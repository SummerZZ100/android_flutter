package cn.com.jsj.lib_base.nano.base;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;


import java.net.NetworkInterface;
import java.net.SocketException;


/**
 * 设备相关工具类
 * Created by donghongyu on 2016/10/21.
 */

public class SaDeviceUtils {

    /**
     * 获取MAC地址
     * <p>
     * 由于6.0之后的系统限制，所以使用Java获取设备网络设备信息的API
     * NetworkInterface.getNetworkInterfaces() ——仍然可以间接地获取到MAC地址。
     *
     * @return 手机MAC地址
     */
    public static String getMac() {
        //MAC地址，有一个默认数据
        String mac = "02:00:00:00:00:00";
        try {
            //获取指定的名称的MAC地址
            NetworkInterface networkInterface = NetworkInterface.getByName("wlan0");

            byte[] addr = networkInterface.getHardwareAddress();

            StringBuilder buf = new StringBuilder();
            for (byte b : addr) {
                buf.append(String.format("%02X:", b));
            }
            if (buf.length() > 0) {
                buf.deleteCharAt(buf.length() - 1);
            }
            mac = buf.toString();
            Log.d("MAC","MAC  wlan0 interfaceName=" + networkInterface.getName() + ", mac=" + mac);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        //返回MAC地址
        return mac;
    }



    /**
     * 获取IMEI
     * @param context 上下文
     * @return 手机IMEI
     */
    public static String getIMEI(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = tm.getDeviceId();
        if ( TextUtils.isEmpty(imei) ) {
            imei = "";
        }
        return imei;
    }

}
