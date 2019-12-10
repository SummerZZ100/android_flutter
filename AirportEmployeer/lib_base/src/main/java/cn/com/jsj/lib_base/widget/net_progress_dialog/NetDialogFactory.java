package cn.com.jsj.lib_base.widget.net_progress_dialog;

import android.app.Activity;
import android.util.Log;

/**
 * 网络请求进度条工厂类
 *
 * @author : wangjing
 * @date : 2019/1/7
 */
public class NetDialogFactory {

    private static int mDialogCounter = 0;
    private static CustomDialog mDialog;


    public static void showDialog(Activity context) {
        if (mDialog == null) {
            mDialog = CustomDialog.getInstance(context);
        }
        if (!mDialog.isShowing()) {
            mDialog.show();
            Log.e("NetDialogFactory", "showDialog: show" );
        }
        mDialogCounter++;
        Log.e("NetDialogFactory", "showDialog: " + mDialogCounter);
        Log.e("NetDialogFactory", "mDialog.toString(): " + mDialog.toString());
    }

    public static void dismissDialog() {
        if (mDialog == null) {
            return;
        }
        if (mDialogCounter > 1) {
            mDialogCounter--;
        } else {
            clearDialog();
        }
        Log.e("NetDialogFactory", "dismissDialog: " + mDialogCounter);
    }


    public static void clearDialog() {
        mDialogCounter = 0;
        if (mDialog == null) {
            return;
        }
        mDialog.dismiss();
        mDialog = null;
    }
}
