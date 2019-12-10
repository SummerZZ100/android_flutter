package cn.com.jsj.lib_base.base;

import android.os.Bundle;
import android.os.StrictMode;

import com.gyf.barlibrary.ImmersionBar;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import cn.com.jsj.lib_base.BuildConfig;
import cn.com.jsj.lib_base.R;
import cn.com.jsj.lib_base.widget.CommonToolbar;
import cn.com.jsj.lib_base.widget.net_progress_dialog.NetDialogFactory;

/**
 * 新建的Activity基类
 *
 * @author : wangjing
 * @date : 2018/12/13
 */
public abstract class BaseJsjActivity extends AppCompatActivity {
    public CommonToolbar mToolbar;
    //有这句话就可以在任何页面使用SVG
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    /**
     * 开发者模式（用于开启 google严苛模式）
     */
    public static boolean DEVELOP_MODE = false;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //默认线下测试会启用google的严苛模式   检查在主线程的耗时操作：网络访问、复杂动画、磁盘读写 等
        if (BuildConfig.DEBUG && DEVELOP_MODE) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()   // or .detectAll() for all detectable problems
                    .penaltyLog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
        }

        super.onCreate(savedInstanceState);
        initImmersionBar();
        setContentView(getLayoutId());
        //初始化BaseLifecycle
        new BaseLifecycle(getLifecycle());
        initToolBar();
        initView();
        initListener();
        initData();
    }

    /**
     * 初始化沉浸式状态栏
     */
    public void initImmersionBar() {
        ImmersionBar.with(this).statusBarDarkFont(true,0.2f).init();
    }

    protected void initToolBar() {
        mToolbar = findViewById(R.id.toolbar_common);
        if (mToolbar == null) {
            return;
        }
        mToolbar.setOnLeftIconClickListener(new CommonToolbar.OnLeftIconClickListener() {
            @Override
            public void onLeftIconClick() {
                onToolbarLeftIconClick();
            }
        });
        initToolbarView();
    }

    public void onToolbarLeftIconClick() {
        onBackPressed();
    }


    public abstract void initToolbarView();
    /**
     * 初始化布局ID
     * @return  view id
     */
    @LayoutRes
    public abstract int getLayoutId();

    /**
     * 初始化视图层，具体调用逻辑自己实现
     */
    protected abstract void initView();

    /**
     * 初始化数据层，具体调用逻辑自己实现
     */
    protected abstract void initData();

    /**
     * 初始化视图层监听，具体调用逻辑自己实现
     */
    protected abstract void initListener();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }
    public void showNetDialog() {
        NetDialogFactory.showDialog(this);
    }

    public void dissmissDialog() {
        NetDialogFactory.dismissDialog();
    }

    @Override
    public void onPause() {
        super.onPause();
        NetDialogFactory.clearDialog();
    }
}
