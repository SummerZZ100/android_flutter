package cn.com.jsj.lib_common.permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


/**
 * <pre>
 * 权限使用工具类
 * </pre>
 */
public final class PermissionUtils {

    private static final List<String> PERMISSIONS = getPermissions();

    private static PermissionUtils sInstance;

    private OnRationaleListener mOnRationaleListener;
    private SimpleCallback mSimpleCallback;
    private FullCallback mFullCallback;
    private ThemeCallback mThemeCallback;
    private Set<String> mPermissions;
    //待申请的
    private List<String> mPermissionsRequest;
    //已经同意的
    private List<String> mPermissionsGranted;
    //暂时拒绝
    private List<String> mPermissionsDenied;
    //永远拒绝-需要进入设置页面去设置
    private List<String> mPermissionsDeniedForever;

    /**
     * Return the permissions used in application.
     *
     * @return the permissions used in application
     */
    public static List<String> getPermissions() {
        return getPermissions(Utils.getApp().getPackageName());
    }

    /**
     * 获取app需要的权限集合
     * Return the permissions used in application.
     *
     * @param packageName The name of the package.
     * @return the permissions used in application
     */
    public static List<String> getPermissions(final String packageName) {
        PackageManager pm = Utils.getApp().getPackageManager();
        try {
            return Arrays.asList(
                    pm.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS)
                            .requestedPermissions
            );
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * 是否获取了这些权限
     * Return whether <em>you</em> have granted the permissions.
     *
     * @param permissions The permissions.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isGranted(final String... permissions) {
        for (String permission : permissions) {
            if (!isGranted(permission)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 5.0以下 默认获取了权限
     *
     * @param permission
     * @return
     */
    private static boolean isGranted(final String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M
                || PackageManager.PERMISSION_GRANTED
                == ContextCompat.checkSelfPermission(Utils.getApp(), permission);
    }

    /**
     * 针对不再提醒的拒绝的权限在再次使用的时候，打开设置页面
     * Launch the application's details settings.
     */
    public static void launchAppDetailsSettings() {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + Utils.getApp().getPackageName()));
        Utils.getApp().startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    /**
     * 调用入口
     * Set the permissions.
     *
     * @param permissions The permissions.
     * @return the single {@link PermissionUtils} instance
     */
    public static PermissionUtils permission(@PermissionConstants.Permission final String... permissions) {
        return new PermissionUtils(permissions);
    }

    private PermissionUtils(final String... permissions) {
        mPermissions = new LinkedHashSet<>();
        for (String permission : permissions) {
            for (String aPermission : PermissionConstants.getPermissions(permission)) {
                if (PERMISSIONS.contains(aPermission)) {
                    mPermissions.add(aPermission);
                }
            }
        }
        sInstance = this;
    }

    /**
     * Set rationale listener.
     *
     * @param listener The rationale listener.
     * @return the single {@link PermissionUtils} instance
     */
    public PermissionUtils rationale(final OnRationaleListener listener) {
        mOnRationaleListener = listener;
        return this;
    }

    /**
     * Set the simple call back.
     *
     * @param callback the simple call back
     * @return the single {@link PermissionUtils} instance
     */
    public PermissionUtils callback(final SimpleCallback callback) {
        mSimpleCallback = callback;
        return this;
    }

    /**
     * Set the full call back.
     *
     * @param callback the full call back
     * @return the single {@link PermissionUtils} instance
     */
    public PermissionUtils callback(final FullCallback callback) {
        mFullCallback = callback;
        return this;
    }

    /**
     * Set the theme callback.
     *
     * @param callback The theme callback.
     * @return the single {@link PermissionUtils} instance
     */
    public PermissionUtils theme(final ThemeCallback callback) {
        mThemeCallback = callback;
        return this;
    }

    /**
     * Start request.
     */
    public void request() {
        mPermissionsGranted = new ArrayList<>();
        mPermissionsRequest = new ArrayList<>();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            mPermissionsGranted.addAll(mPermissions);
            requestCallback();
        } else {
            for (String permission : mPermissions) {
                if (hasSelfPermission(permission)) {
                    mPermissionsGranted.add(permission);
                } else {
                    mPermissionsRequest.add(permission);
                }
            }
            if (mPermissionsRequest.isEmpty()) {
                requestCallback();
            } else {
                startPermissionActivity();
            }
        }
    }

    private static boolean hasSelfPermission( String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && "Xiaomi".equalsIgnoreCase(Build.MANUFACTURER)) {
            return hasSelfPermissionForXiaomi(Utils.getApp(), permission);
        }
        try {
            return PermissionChecker.checkSelfPermission(Utils.getApp(), permission) == PackageManager.PERMISSION_GRANTED;
        } catch (RuntimeException t) {
            return false;
        }
    }

    private static boolean hasSelfPermissionForXiaomi(Context context, String permission) {
        String permissionToOp = AppOpsManagerCompat.permissionToOp(permission);
        if (permissionToOp == null) {
            // in case of normal permissions(e.g. INTERNET)
            return true;
        }
        int noteOp = AppOpsManagerCompat.noteOp(context, permissionToOp, Process.myUid(), context.getPackageName());
        return noteOp == AppOpsManagerCompat.MODE_ALLOWED && PermissionChecker.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void startPermissionActivity() {
        mPermissionsDenied = new ArrayList<>();
        mPermissionsDeniedForever = new ArrayList<>();
        PermissionActivity.start(Utils.getApp());
    }

    /**
     * Android 提供了一个实用程序方法，即 shouldShowRequestPermissionRationale()。如果应用之前请求过此权限但用户拒绝了请求，此方法将返回 true。
     * 注：如果用户在过去拒绝了权限请求，并在权限请求系统对话框中选择了 Don’t ask again 选项，此方法将返回 false。如果设备规范禁止应用具有该权限，此方法也会返回 false。
     * 根据测试shouldShowRequestPermissionRationale的返回值主要以下几种情况 ：
     * 第一次打开App时	false
     * 上次弹出权限点击了禁止（但没有勾选“下次不在询问”）	true
     * 上次选择禁止并勾选：下次不在询问	false
     *
     * @param activity
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean rationale(final Activity activity) {
        boolean isRationale = false;
        if (mOnRationaleListener != null) {
            for (String permission : mPermissionsRequest) {
                if (activity.shouldShowRequestPermissionRationale(permission)) {
                    getPermissionsStatus(activity);
                    mOnRationaleListener.rationale(new OnRationaleListener.ShouldRequest() {
                        @Override
                        public void again(boolean again) {
                            if (again) {
                                startPermissionActivity();
                            } else {
                                requestCallback();
                            }
                        }
                    });
                    isRationale = true;
                    break;
                }
            }
            mOnRationaleListener = null;
        }
        return isRationale;
    }

    /**
     * 获取目前获取到的权限状态
     * @param activity
     */
    private void getPermissionsStatus(final Activity activity) {
        for (String permission : mPermissionsRequest) {
            if (isGranted(permission)) {
                mPermissionsGranted.add(permission);
            } else {
                mPermissionsDenied.add(permission);
                if (!activity.shouldShowRequestPermissionRationale(permission)) {
                    mPermissionsDeniedForever.add(permission);
                }
            }
        }
    }

    /**
     * 监听操作
     */
    private void requestCallback() {
        if (mSimpleCallback != null) {
            if (mPermissionsRequest.size() == 0
                    || mPermissions.size() == mPermissionsGranted.size()) {
                mSimpleCallback.onGranted();
            } else {
                if (!mPermissionsDenied.isEmpty()) {
                    mSimpleCallback.onDenied();
                }
            }
            mSimpleCallback = null;
        }
        if (mFullCallback != null) {
            if (mPermissionsRequest.size() == 0
                    || mPermissions.size() == mPermissionsGranted.size()) {
                mFullCallback.onGranted(mPermissionsGranted);
            } else {
                if (!mPermissionsDenied.isEmpty()) {
                    mFullCallback.onDenied(mPermissionsDeniedForever, mPermissionsDenied);
                }
            }
            mFullCallback = null;
        }
        mOnRationaleListener = null;
        mThemeCallback = null;
    }

    private void onRequestPermissionsResult(final Activity activity) {
        getPermissionsStatus(activity);
        requestCallback();
    }



    public static class PermissionActivity extends Activity {

        public static void start(final Context context) {
            Intent starter = new Intent(context, PermissionActivity.class);
            starter.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(starter);
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                    | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);
            if (sInstance == null) {
                super.onCreate(savedInstanceState);
                Log.e("PermissionUtils", "request permissions failed");
                finish();
                return;
            }
            if (sInstance.mThemeCallback != null) {
                sInstance.mThemeCallback.onActivityCreate(this);
            }
            super.onCreate(savedInstanceState);

            if (sInstance.rationale(this)) {
                finish();
                return;
            }
            if (sInstance.mPermissionsRequest != null) {
                int size = sInstance.mPermissionsRequest.size();
                if (size <= 0) {
                    finish();
                    return;
                }
                requestPermissions(sInstance.mPermissionsRequest.toArray(new String[size]), 1);
            }
        }

        @Override
        public void onRequestPermissionsResult(int requestCode,
                                               @NonNull String[] permissions,
                                               @NonNull int[] grantResults) {
            sInstance.onRequestPermissionsResult(this);
            finish();
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent ev) {
            finish();
            return true;
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // interface
    ///////////////////////////////////////////////////////////////////////////

    public interface OnRationaleListener {

        void rationale(ShouldRequest shouldRequest);

        interface ShouldRequest {
            void again(boolean again);
        }
    }

    public interface SimpleCallback {
        void onGranted();

        void onDenied();
    }

    /**
     * 获取各种权限状态
     */
    public interface FullCallback {
        void onGranted(List<String> permissionsGranted);

        void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied);
    }

    public interface ThemeCallback {
        void onActivityCreate(Activity activity);
    }
}