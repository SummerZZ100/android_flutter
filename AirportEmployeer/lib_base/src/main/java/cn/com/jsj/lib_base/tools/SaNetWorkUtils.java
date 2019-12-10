package cn.com.jsj.lib_base.tools;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.List;


/**
 * 网络 工具类<Br>
 * <p>
 * SaNetWorkUtils.java
 *
 * @Date 2015年6月26日上午1:22:57
 * @Author Donghongyu 1358506549@qq.com
 * @Version v1.0.0
 */
public class SaNetWorkUtils {
    /**
     * Log 输出标签
     */
    public static String TAG = SaNetWorkUtils.class.getName();

    /**
     * 接受网络状态的广播Action
     */
    public static final String NET_BROADCAST_ACTION = "com.network.state.action";

    public static final String NET_STATE_NAME = "network_state";

    /**
     * 实时更新网络状态<br>
     * -1为网络无连接<br>
     * 1为WIFI<br>
     * 2为移动网络<br>
     */
    public static int CURRENT_NETWORK_STATE = -1;

    /**
     * 判断网络是否连接
     *
     * @param context
     * @return 有网络返回true；无网络返回false
     */
    public static boolean isConnected(Context context) {
        if (context != null) {
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            if (null != connectivity) {
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (null != info && info.isConnected()) {
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        SaLogUtils.i(TAG, "当前网络可用...");
                        return true;
                    }
                }
            }
        }

        SaLogUtils.e(TAG, "当前网络不可用....");
        return false;
    }

    /**
     * 判断是否是wifi连接
     *
     * @param context
     * @return 如果为wifi返回true；否则返回false
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm == null) {
            SaLogUtils.i(TAG, "当前网络----->不可用");
            return false;
        }
        boolean isWifi = cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
        if (isWifi)
            SaLogUtils.i(TAG, "当前网络----->WIFI环境");
        else
            SaLogUtils.i(TAG, "当前网络----->非WIFI环境");

        return isWifi;
    }

    /**
     * 判断MOBILE网络是否可用
     *
     * @param context
     * @return
     * @throws Exception
     */
    public static boolean isMobileDataEnable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isMobileDataEnable = false;
        isMobileDataEnable = connectivityManager.getNetworkInfo(
                ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();

        return isMobileDataEnable;
    }

    /**
     * 打开网络设置界面
     *
     * @param activity 当前界面
     */
    public static void openSetting(Context activity) {
        Intent intent = new Intent("/");
        // 判断手机系统的版本 即API大于10 就是3.0或以上版本
        if (android.os.Build.VERSION.SDK_INT > 10) {
            /*intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);*/
            intent = new Intent(Settings.ACTION_SETTINGS);
        } else {
            intent = new Intent();
            ComponentName component = new ComponentName("com.android.settings",
                    "com.android.settings.Settings");
            intent.setComponent(component);
            intent.setAction("android.intent.action.VIEW");
        }
        activity.startActivity(intent);
    }

    /**
     * 开启服务,实时监听网络变化 需要自己在清单文件中配置服务<br>
     * 然后把对应的Action传入<br>
     * 服务类:com.martin.utils.net.NetWrokListener
     *
     * @param context
     * @param action
     */
    public static void startNetService(Context context, String action) {
        // 注册广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NET_BROADCAST_ACTION);
        context.registerReceiver(mReceiver, intentFilter);
        // 开启服务
        Intent intent = new Intent();
        SaLogUtils.d(TAG, "开启网络监听服务");
        intent.setAction(action);
        context.bindService(intent, new ServiceConnection() {

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

            }
        }, Context.BIND_AUTO_CREATE);
    }

    // 接受服务上发过来的广播
    private static BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                CURRENT_NETWORK_STATE = (Integer) intent.getExtras().get(
                        NET_STATE_NAME);
                switch (CURRENT_NETWORK_STATE) {
                    case -1:
                        SaLogUtils.d(TAG, "网络更改为 无网络  CURRENT_NETWORK_STATE ="
                                + CURRENT_NETWORK_STATE);
                        break;
                    case 1:
                        SaLogUtils.d(TAG, "网络更改为 WIFI网络  CURRENT_NETWORK_STATE="
                                + CURRENT_NETWORK_STATE);
                        break;
                    case 2:
                        SaLogUtils.d(TAG, "网络更改为 移动网络  CURRENT_NETWORK_STATE ="
                                + CURRENT_NETWORK_STATE);
                        break;

                    default:
                        break;
                }
            }
        }
    };

    /**
     * 描述：打开wifi.
     *
     * @param context
     * @param enabled
     * @return
     */
    public static void setWifiEnabled(Context context, boolean enabled) {
        WifiManager wifiManager = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        if (enabled) {
            wifiManager.setWifiEnabled(true);
        } else {
            wifiManager.setWifiEnabled(false);
        }
    }

    /**
     * 描述：得到所有的WiFi列表.
     *
     * @param context
     * @return
     */
    public static List<ScanResult> getScanResults(Context context) {
        WifiManager wifiManager = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        List<ScanResult> list = null;
        // 开始扫描WiFi
        boolean f = wifiManager.startScan();
        if (!f) {
            getScanResults(context);
        } else {
            // 得到扫描结果
            list = wifiManager.getScanResults();
        }
        return list;
    }

    /**
     * 描述：根据SSID过滤扫描结果.
     *
     * @param context
     * @param bssid
     * @return
     */
    public static ScanResult getScanResultsByBSSID(Context context, String bssid) {
        WifiManager wifiManager = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        ScanResult scanResult = null;
        // 开始扫描WiFi
        boolean f = wifiManager.startScan();
        if (!f) {
            getScanResultsByBSSID(context, bssid);
        }
        // 得到扫描结果
        List<ScanResult> list = wifiManager.getScanResults();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                // 得到扫描结果
                scanResult = list.get(i);
                if (scanResult.BSSID.equals(bssid)) {
                    break;
                }
            }
        }
        SaLogUtils.d(TAG, "根据SSID过滤扫描结果>>>" + scanResult);
        return scanResult;
    }

    /**
     * 描述：获取连接的WIFI信息.
     *
     * @param context
     * @return
     */
    public static WifiInfo getConnectionInfo(Context context) {
        WifiManager wifiManager = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        SaLogUtils.d(TAG, "获取连接的WIFI信息>>>" + wifiInfo);
        return wifiInfo;
    }


    /**
     * 网络IP
     *
     * @return 3G网络IP
     */
    public static String getIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()
                            && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0.0.0.0";
    }

    public static final int NETWORK_TYPE_NONE = -0x1;  // 断网情况
    public static final int NETWORK_TYPE_WIFI = 0x1;   // WiFi模式
    public static final int NETWORK_TYPE_MOBILE = 0x2; // gprs模式

    /**
     * 获取网络连接类型
     *
     * @param mContext 上下文
     * @return 网络连接类型
     * NETWORK_TYPE_NONE = -0x1;  // 断网情况
     * NETWORK_TYPE_WIFI = 0x1;   // WiFi模式
     * NETWOKR_TYPE_MOBILE = 0x2; // gprs模式
     */
    public static int getConnectType(Context mContext) {
        ConnectivityManager connManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI); // wifi
        NetworkInfo gprs = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE); // gprs
        if (wifi != null && wifi.getState() == NetworkInfo.State.CONNECTED) {
            Log.d(TAG, "Current net type:  WIFI.");
            return NETWORK_TYPE_WIFI;
        } else if (gprs != null && gprs.getState() == NetworkInfo.State.CONNECTED) {
            Log.d(TAG, "Current net type:  MOBILE.");
            return NETWORK_TYPE_MOBILE;
        }
        Log.e(TAG, "Current net type:  NONE.");
        return NETWORK_TYPE_NONE;
    }

    /**
     * 获取当前网络名称；
     *
     * @param mcontext 上下文
     * @return 如果是中国移动WAP设置：APN：CMWAP；代理：10.0.0.172；端口：80
     * 中国联通WAP设置：APN：UNIWAP；代理：10.0.0.172；端口：80
     * 中国联通WAP设置（3G）：APN：3GWAP；代理：10.0.0.172；端口：80
     * 中国电信WAP设置：APN(或者接入点名称)：CTWAP；代理：10.0.0.200；端口：80
     */
    public static String getCurrentApnInUse(Context mcontext) {
        String name = "no";
        ConnectivityManager manager = (ConnectivityManager) mcontext.getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            NetworkInfo activeNetInfo = manager.getActiveNetworkInfo();
            if (activeNetInfo != null && activeNetInfo.isAvailable()) {
                name = activeNetInfo.getExtraInfo();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        SaLogUtils.i(TAG, "----当前连接网络名称为-----" + name);
        return name;
    }

    /***
     * 检查网络状态，如果没有连接网络弹框提示，
     * 如果点击“设置”跳转设置，点击“知道了”关闭对话框，
     * 使用者自己实现知道了的逻辑，一般是关闭打开的Activity或Fragment依赖的Activity，
     * 具体情况具体操作。
     *
     * @param context               上下文
     * @param negativeClickListener 知道了（左边按钮），触发的事件，没有事件可以传，null
     */
    public static void checkNetWorkStateWithDialog(final Context context,
                                                   View.OnClickListener negativeClickListener) {
        switch (getConnectType(context)) {
            case NETWORK_TYPE_NONE:
                SaDialogUtils.alertDialog(
                        context,
                        "启用蜂窝数据或WI-FI来访问数据",
                        "设置", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //打开设置界面
                                SaNetWorkUtils.openSetting(context);
                            }
                        },
                        "知道了",
                        negativeClickListener,
                        false);
                break;
            case NETWORK_TYPE_MOBILE:
                break;
            case NETWORK_TYPE_WIFI:
                break;
        }
    }


    /**
     * 打开网络设置
     *
     * @param activity
     */
    public static void openSetting(Activity activity) {
        Intent intent = new Intent("/");
        // 判断手机系统的版本 即API大于10 就是3.0或以上版本
        if (android.os.Build.VERSION.SDK_INT > 10) {
            /*intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);*/
            intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
        } else {
            intent = new Intent();
            ComponentName component = new ComponentName("com.android.settings",
                    "com.android.settings.WirelessSettings");
            intent.setComponent(component);
            intent.setAction("android.intent.action.VIEW");
        }
        activity.startActivityForResult(intent, 22);
    }
}
