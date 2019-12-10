package cn.com.jsj.lib_base.tools;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import cn.com.jsj.lib_base.base.BaseKTApplication;


/**
 * Toast工具类.在线程中显示Toast
 * SaToastUtils.java
 *
 * @Date 2015年6月26日上午1:22:34
 * @Author Donghongyu 1358506549@qq.com
 * @Version v1.0.0
 */
public class SaToastUtils {

    /**
     * 上下文.
     */
    private static Context mContext = null;

    /**
     * 显示Toast.
     */
    public static final int SHOW_TOAST = 0;

    /**
     * 主要Handler类，在线程中可用 what：0.提示文本信息
     */
    private static Handler baseHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_TOAST:
                    showToast(mContext, msg.getData().getString("TEXT"));
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 描述：Toast提示文本.
     *
     * @param text 文本
     */

    public static void showToast(String text) {
        if (!TextUtils.isEmpty(text)) {
            Toast.makeText(BaseKTApplication.Companion.getInstance(), text, Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 描述：Toast提示文本.
     *
     * @param text 文本
     */

    public static void showToast(String text, int gravity ) {
        if (!TextUtils.isEmpty(text)) {
            Toast toast = Toast.makeText(BaseKTApplication.Companion.getInstance(), text, Toast.LENGTH_SHORT);
            toast.setGravity(gravity, 0, 0);
            toast.show();
        }
    }

    public static void showToast(String text, int duration, int gravity , int offSetX, int offsetY) {
        if (!TextUtils.isEmpty(text)) {
            Toast toast = Toast.makeText(BaseKTApplication.Companion.getInstance(), text, duration);
            toast.setGravity(gravity, offSetX, offsetY);
            toast.show();
        }
    }

    /**
     * 描述：Toast提示文本.
     *
     * @param text 文本
     */
    public static void showToast(Context context, String text) {
        mContext = context;
        if (!TextUtils.isEmpty(text)) {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 描述：Toast提示文本.
     *
     * @param resId 文本的资源ID
     */
    public static void showToast(Context context, int resId) {
        mContext = context;
        Toast.makeText(context, "" + context.getResources().getText(resId), Toast.LENGTH_SHORT).show();
    }

    /**
     * 描述：在线程中提示文本信息.
     *
     * @param resId 要提示的字符串资源ID，消息what值为0,
     */
    public static void showToastInThread(Context context, int resId) {
        mContext = context;
        Message msg = baseHandler.obtainMessage(SHOW_TOAST);
        Bundle bundle = new Bundle();
        bundle.putString("TEXT", context.getResources().getString(resId));
        msg.setData(bundle);
        baseHandler.sendMessage(msg);
    }


    /**
     * 描述：在线程中提示文本信息.
     *
     * @param context 消息what值为0
     * @param text
     */
    public static void showToastInThread(Context context, String text) {
        mContext = context;
        Message msg = baseHandler.obtainMessage(SHOW_TOAST);
        Bundle bundle = new Bundle();
        bundle.putString("TEXT", text);
        msg.setData(bundle);
        baseHandler.sendMessage(msg);
    }


}
