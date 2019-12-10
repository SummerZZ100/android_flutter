package cn.com.jsj.lib_base.tools;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;


/**
 * 对话框弹出帮助类，汇集了多种对话框的简单调用
 * Created by donghongyu on 2016/6/6.
 */
public class SaDialogUtils {

    private static SaAlertDialog mSaAlertDialog;

    /**
     * 通用的提示框，只有一个按钮和内容
     *
     * @param context               提示对话框绑定的上下文
     * @param content               提示内容
     * @param btnStrPositive        按钮的文案，可以为null，默认使用确定
     * @param clickListenerPositive 点击按钮触发的动作 ，可以为null，默认无动作，并关闭对话框
     * @param isCancelable          是否点击对话框周边可以取消对话框的展示,true-可以，false-不可以
     */
    public static SaAlertDialog alertDialog(
            Context context,
            String content,
            String btnStrPositive,
            View.OnClickListener clickListenerPositive,
            boolean isCancelable) {
        if (mSaAlertDialog != null) {
            mSaAlertDialog.dismiss();
        }
        mSaAlertDialog = new SaAlertDialog(context).builder()
                .setMsg(content)
                .setPositiveButton(btnStrPositive,
                        clickListenerPositive != null ? clickListenerPositive : new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).setCancelable(isCancelable).show();
        return mSaAlertDialog;
    }

    /**
     * 通用的提示框，带有一个按钮，标题，内容
     *
     * @param context       提示对话框绑定的上下文
     * @param title         提示标题
     * @param content       提示内容
     * @param btnStr        按钮的文案，可以为null，默认使用确定
     * @param clickListener 点击按钮触发的动作 ，可以为null，默认无动作，并关闭对话框
     * @param isCancelable  是否点击对话框周边可以取消对话框的展示,true-可以，false-不可以
     */
    public static SaAlertDialog alertDialog(
            Context context,
            String title,
            String content,
            String btnStr,
            View.OnClickListener clickListener,
            boolean isCancelable) {
        if (mSaAlertDialog != null) {
            mSaAlertDialog.dismiss();
        }
        mSaAlertDialog = new SaAlertDialog(context).builder().setTitle(title)
                .setMsg(content)
                .setPositiveButton(btnStr,
                        clickListener != null ? clickListener : new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).setCancelable(isCancelable).show();
        return mSaAlertDialog;
    }

    /**
     * 通用的提示框,带有两个按钮，内容
     *
     * @param context               提示框绑定的上下文
     * @param content               提示框内容，不可以为null，默认使用 "程序猿忘记给你写提示内容了。"提示开发人员
     * @param positiveBtnStr        确定按钮文案，可以为null，默认使用“确定”
     * @param positiveClickListener 确定按钮点击后的触发动作 ，可以为null，默认无动作，并关闭对话框
     * @param negativeBtnStr        取消按钮文案，可以为null，默认使用“取消”
     * @param negativeClickListener 取消按钮点击后的触发动作 ，可以为null，默认无动作，并关闭对话框
     * @param isCancelable          是否点击对话框周边可以取消对话框的展示,true-可以，false-不可以
     */
    public static SaAlertDialog alertDialog(
            Context context,
            String content,
            String positiveBtnStr,
            View.OnClickListener positiveClickListener,
            String negativeBtnStr,
            View.OnClickListener negativeClickListener,
            boolean isCancelable) {
        if (mSaAlertDialog != null) {
            mSaAlertDialog.dismiss();
        }

        mSaAlertDialog = new SaAlertDialog(context).builder()
                .setMsg(content)
                .setPositiveButton(positiveBtnStr,
                        positiveClickListener != null ? positiveClickListener : new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                .setNegativeButton(negativeBtnStr, negativeClickListener != null ? negativeClickListener : new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).setCancelable(isCancelable).show();
        return mSaAlertDialog;
    }

    /**
     * 通用的提示框,带有两个按钮,标题，内容
     *
     * @param context               提示框绑定的上下文
     * @param title                 提示框标题，可以为null，默认使用“提示”
     * @param content               提示框内容，不可以为null，默认使用 "程序猿忘记给你写提示内容了。"提示开发人员
     * @param positiveBtnStr        确定按钮文案，可以为null，默认使用“确定”
     * @param positiveClickListener 确定按钮点击后的触发动作 ，可以为null，默认无动作，并关闭对话框
     * @param negativeBtnStr        取消按钮文案，可以为null，默认使用“取消”
     * @param negativeClickListener 取消按钮点击后的触发动作 ，可以为null，默认无动作，并关闭对话框
     * @param isCancelable          是否点击对话框周边可以取消对话框的展示,true-可以，false-不可以
     */
    public static SaAlertDialog alertDialog(
            Context context,
            String title,
            String content,
            String positiveBtnStr,
            View.OnClickListener positiveClickListener,
            String negativeBtnStr,
            View.OnClickListener negativeClickListener,
            boolean isCancelable) {

        if (mSaAlertDialog != null) {
            mSaAlertDialog.dismiss();
        }

        mSaAlertDialog = new SaAlertDialog(context).builder().setTitle(title)
                .setMsg(content)
                .setPositiveButton(positiveBtnStr,
                        positiveClickListener != null ? positiveClickListener : new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                .setNegativeButton(negativeBtnStr, negativeClickListener != null ? negativeClickListener : new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).setCancelable(isCancelable).show();

        return mSaAlertDialog;
    }

    /**
     * 通用的提示框,没有按钮,只有标题和内容
     *
     * @param context      提示框绑定的上下文
     * @param title        提示框标题，可以为null，默认使用“提示”
     * @param content      提示框内容，不可以为null，默认使用 "程序猿忘记给你写提示内容了。"提示开发人员
     * @param dismissTime  自动关闭时间 单位：毫秒
     * @param isCancelable 是否点击对话框周边可以取消对话框的展示,true-可以，false-不可以
     */
    public static SaAlertDialog alertDialog(
            Context context,
            String title,
            String content,
            int dismissTime,
            boolean isCancelable) {
        if (mSaAlertDialog != null) {
            mSaAlertDialog.dismiss();
        }
        mSaAlertDialog = new SaAlertDialog(context).builder().setTitle(title)
                .setMsg(content)
                .setCancelable(isCancelable).setAutoDismissTime(dismissTime).show();

        return mSaAlertDialog;
    }

    /**
     * 通用的提示框,没有按钮,只有内容
     *
     * @param context      提示框绑定的上下文
     * @param content      提示框内容，不可以为null，默认使用 "程序猿忘记给你写提示内容了。"提示开发人员
     * @param dismissTime  自动关闭时间 单位：毫秒
     * @param isCancelable 是否点击对话框周边可以取消对话框的展示,true-可以，false-不可以
     */
    public static SaAlertDialog alertDialog(
            Context context,
            String content,
            int dismissTime,
            boolean isCancelable) {
        if (mSaAlertDialog != null) {
            mSaAlertDialog.dismiss();
        }
        mSaAlertDialog = new SaAlertDialog(context).builder()
                .setMsg(content)
                .setCancelable(isCancelable).setAutoDismissTime(dismissTime).show();
        return mSaAlertDialog;
    }

    /**
     * 通用的提示框,带有两个按钮,标题，内容
     *
     * @param context               提示框绑定的上下文
     * @param view                  提示框绑定的布局
     * @param content               提示框内容，不可以为null，默认使用 "程序猿忘记给你写提示内容了。"提示开发人员
     * @param positiveBtnStr        确定按钮文案，可以为null，默认使用“确定”
     * @param positiveClickListener 确定按钮点击后的触发动作 ，可以为null，默认无动作，并关闭对话框
     * @param negativeBtnStr        取消按钮文案，可以为null，默认使用“取消”
     * @param negativeClickListener 取消按钮点击后的触发动作 ，可以为null，默认无动作，并关闭对话框
     * @param isCancelable          是否点击对话框周边可以取消对话框的展示,true-可以，false-不可以
     */
    public static SaAlertDialog alertViewDialog(
            Context context,
            View view,
            String content,
            String positiveBtnStr,
            View.OnClickListener positiveClickListener,
            String negativeBtnStr,
            View.OnClickListener negativeClickListener,
            boolean isCancelable) {

        if (mSaAlertDialog != null) {
            mSaAlertDialog.dismiss();
        }

        mSaAlertDialog = new SaAlertDialog(context).builder()
                .setMsg(content)
                .setContentView(view)
                .setConfirmButton(positiveBtnStr,
                        positiveClickListener != null ? positiveClickListener : new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                .setNegativeButton(negativeBtnStr, negativeClickListener != null ? negativeClickListener : new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).setCancelable(isCancelable).show();

        return mSaAlertDialog;
    }

    //######################################实际使用推荐使用下面的方法##################################################################

    /**
     * 通用的提示框,带有两个按钮,标题，内容
     *
     * @param context               提示框绑定的上下文
     * @param view                  提示框绑定的布局
     * @param content               提示框内容，不可以为null，默认使用 "程序猿忘记给你写提示内容了。"提示开发人员
     * @param positiveBtnStr        确定按钮文案，可以为null，默认使用“确定”
     * @param positiveClickListener 确定按钮点击后的触发动作 ，可以为null，默认无动作，并关闭对话框
     * @param negativeBtnStr        取消按钮文案，可以为null，默认使用“取消”
     * @param negativeClickListener 取消按钮点击后的触发动作 ，可以为null，默认无动作，并关闭对话框
     * @param isCancelable          是否点击对话框周边可以取消对话框的展示,true-可以，false-不可以
     * @return 当前初始化出来的对话框对象
     */
    public static SaAlertDialog alertTitleContentTwoButton(
            Context context,
            View view,
            @NonNull String content,
            String positiveBtnStr,
            View.OnClickListener positiveClickListener,
            String negativeBtnStr,
            View.OnClickListener negativeClickListener,
            boolean isCancelable) {


        return alertViewDialog(
                context,
                view,
                content,
                positiveBtnStr,
                positiveClickListener,
                negativeBtnStr,
                negativeClickListener,
                isCancelable);
    }

    /**
     * 通用的提示框,没有按钮,只有内容
     *
     * @param context      提示框绑定的上下文
     * @param content      提示框内容，不可以为null，默认使用 "程序猿忘记给你写提示内容了。"提示开发人员
     * @param dismissTime  自动关闭时间 单位：毫秒
     * @param isCancelable 是否点击对话框周边可以取消对话框的展示,true-可以，false-不可以
     * @return 当前初始化出来的对话框对象
     */
    public static SaAlertDialog alertContent(
            Context context,
            @NonNull String content,
            int dismissTime,
            boolean isCancelable) {
        return alertDialog(context, content, dismissTime, isCancelable);
    }

    /**
     * 通用的提示框,没有按钮,只有标题和内容
     *
     * @param context      提示框绑定的上下文
     * @param content      提示框内容，不可以为null，默认使用 "程序猿忘记给你写提示内容了。"提示开发人员
     * @param dismissTime  自动关闭时间 单位：毫秒
     * @param isCancelable 是否点击对话框周边可以取消对话框的展示,true-可以，false-不可以
     * @return 当前初始化出来的对话框对象
     */
    public static SaAlertDialog alertTitleContent(
            Context context,
            String title,
            @NonNull String content,
            int dismissTime,
            boolean isCancelable) {
        return alertDialog(context, title, content, dismissTime, isCancelable);
    }

    /**
     * 通用的提示框,带有两个按钮,标题，内容
     *
     * @param context               提示框绑定的上下文
     * @param title                 提示框标题，可以为null，默认使用“提示”
     * @param content               提示框内容，不可以为null，默认使用 "程序猿忘记给你写提示内容了。"提示开发人员
     * @param positiveBtnStr        确定按钮文案，可以为null，默认使用“确定”
     * @param positiveClickListener 确定按钮点击后的触发动作 ，可以为null，默认无动作，并关闭对话框
     * @param negativeBtnStr        取消按钮文案，可以为null，默认使用“取消”
     * @param negativeClickListener 取消按钮点击后的触发动作 ，可以为null，默认无动作，并关闭对话框
     * @param isCancelable          是否点击对话框周边可以取消对话框的展示,true-可以，false-不可以
     * @return 当前初始化出来的对话框对象
     */
    public static SaAlertDialog alertTitleContentTwoButton(
            Context context,
            String title,
            @NonNull String content,
            String positiveBtnStr,
            View.OnClickListener positiveClickListener,
            String negativeBtnStr,
            View.OnClickListener negativeClickListener,
            boolean isCancelable) {
        return alertDialog(
                context,
                title,
                content,
                positiveBtnStr,
                positiveClickListener,
                negativeBtnStr,
                negativeClickListener, isCancelable);
    }


    /**
     * 通用的提示框,带有两个按钮，内容
     *
     * @param context               提示框绑定的上下文
     * @param content               提示框内容，不可以为null，默认使用 "程序猿忘记给你写提示内容了。"提示开发人员
     * @param positiveBtnStr        确定按钮文案，可以为null，默认使用“确定”
     * @param positiveClickListener 确定按钮点击后的触发动作 ，可以为null，默认无动作，并关闭对话框
     * @param negativeBtnStr        取消按钮文案，可以为null，默认使用“取消”
     * @param negativeClickListener 取消按钮点击后的触发动作 ，可以为null，默认无动作，并关闭对话框
     * @param isCancelable          是否点击对话框周边可以取消对话框的展示,true-可以，false-不可以
     */
    public static SaAlertDialog alertContentTwoButton(
            Context context,
            @NonNull String content,
            String positiveBtnStr,
            View.OnClickListener positiveClickListener,
            String negativeBtnStr,
            View.OnClickListener negativeClickListener,
            boolean isCancelable) {
        return alertDialog(context,
                content,
                positiveBtnStr,
                positiveClickListener,
                negativeBtnStr,
                negativeClickListener,
                isCancelable);
    }


    /**
     * 通用的提示框，带有一个按钮，标题，内容
     *
     * @param context       提示对话框绑定的上下文
     * @param title         提示标题
     * @param content       提示内容
     * @param btnStr        按钮的文案，可以为null，默认使用确定
     * @param clickListener 点击按钮触发的动作 ，可以为null，默认无动作，并关闭对话框
     * @param isCancelable  是否点击对话框周边可以取消对话框的展示,true-可以，false-不可以
     */
    public static SaAlertDialog alertTitleContentOneButton(
            Context context,
            String title,
            @NonNull String content,
            String btnStr,
            View.OnClickListener clickListener,
            boolean isCancelable) {

        return alertDialog(context,
                title,
                content,
                btnStr,
                clickListener,
                isCancelable);
    }


    /**
     * 通用的提示框，只有一个按钮和内容
     *
     * @param context               提示对话框绑定的上下文
     * @param content               提示内容
     * @param btnStrPositive        按钮的文案，可以为null，默认使用确定
     * @param clickListenerPositive 点击按钮触发的动作 ，可以为null，默认无动作，并关闭对话框
     * @param isCancelable          是否点击对话框周边可以取消对话框的展示,true-可以，false-不可以
     */
    public static SaAlertDialog alertContentOneButton(
            Context context,
            @NonNull String content,
            String btnStrPositive,
            View.OnClickListener clickListenerPositive,
            boolean isCancelable) {
        return alertDialog(context, content, btnStrPositive, clickListenerPositive, isCancelable);
    }

    /**
     * 通用的提示框，只有一个按钮和内容
     *
     * @param context 提示对话框绑定的上下文
     * @param content 提示内容
     */
    public static SaAlertDialog alertContentOneButton(
            Context context,
            @NonNull String content) {
        return alertDialog(context, content, null, null, false);
    }
}
