package cn.com.jsj.lib_base.tools;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import cn.com.jsj.lib_base.base.BaseKTApplication;

/**
 * todo 描述：模块名_具体页面描述
 *
 * @author : wangjing
 * @date : 2019/1/28
 */
public class ClipboardUtils {

    public static void clipStirng(String str) {
        ClipData clipData = ClipData.newPlainText("", str);
        ClipboardManager systemService = (ClipboardManager) BaseKTApplication.Companion.getInstance()
                .getSystemService(Context.CLIPBOARD_SERVICE);
        systemService.setPrimaryClip(clipData);
    }
}
