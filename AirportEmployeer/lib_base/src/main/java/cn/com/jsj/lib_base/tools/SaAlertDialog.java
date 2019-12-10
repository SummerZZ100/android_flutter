package cn.com.jsj.lib_base.tools;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import cn.com.jsj.lib_base.R;


/**
 * 仿IOS提示对话框
 */
public class SaAlertDialog {
    private SaAlertDialog mSaAlertDialog;
    private Context context;
    private Dialog dialog;
    private LinearLayout lLayout_bg;
    private LinearLayout ll_btn_group;
    private TextView txt_title;
    private TextView txt_msg;
    private Button btn_neg;
    private Button btn_pos;
    private ImageView img_line;
    private Display display;
    private boolean showTitle = false;
    private boolean showMsg = false;
    private boolean showContentView = false;
    private boolean showPosBtn = false;
    private boolean showNegBtn = false;


    /**
     * 倒计时总时长，默认60000毫秒=60秒
     */
    private int mMillisInFuture = 500;


    /**
     * 倒计时间隔时常，默认1000毫秒=1秒
     */
    private int mCountDownInterval = 1;

    private Timer t;

    private TimerTask tt;
    private FrameLayout mFrameLayout;


    public SaAlertDialog(Context context) {
        this.context = context;
        mSaAlertDialog = this;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public SaAlertDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.view_alertdialog, null);

        // 获取自定义Dialog布局中的控件
        lLayout_bg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
        ll_btn_group = (LinearLayout) view.findViewById(R.id.ll_btn_group);
        mFrameLayout = (FrameLayout) view.findViewById(R.id.fl_rootview);
        txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_title.setVisibility(View.GONE);
        txt_msg = (TextView) view.findViewById(R.id.txt_msg);
        txt_msg.setVisibility(View.GONE);
        btn_neg = (Button) view.findViewById(R.id.btn_neg);
        btn_neg.setVisibility(View.GONE);
        btn_pos = (Button) view.findViewById(R.id.btn_pos);
        btn_pos.setVisibility(View.GONE);
        img_line = (ImageView) view.findViewById(R.id.img_line);
        img_line.setVisibility(View.GONE);

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);

        // 调整dialog背景大小
        lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.85), LayoutParams.WRAP_CONTENT));

        return this;
    }

    /**
     * 设置自动关闭对话框的时间
     *
     * @param autoDismissTime 关闭时间长度
     * @return 当前帮助类
     */
    public SaAlertDialog setAutoDismissTime(int autoDismissTime) {
        mMillisInFuture = autoDismissTime;
        startTimer();
        return this;
    }

    public SaAlertDialog setTitle(String title) {
        showTitle = true;
        if (TextUtils.isEmpty(title)) {
            txt_title.setText("标题");
        } else {
            txt_title.setText(title);
        }
        return this;
    }

    public SaAlertDialog setMsg(String msg) {
        showMsg = true;
        if (TextUtils.isEmpty(msg)) {
            txt_msg.setText(msg);
        } else {
            txt_msg.setText(msg);
        }
        return this;
    }

    public SaAlertDialog setContentView(View view) {
        showContentView = true;
        if (view == null) {
            showContentView = false;
        } else {
            mFrameLayout.addView(view);
        }
        return this;
    }

    public SaAlertDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    /**
     * 确定按钮自带取消dialog
     *
     * @param text
     * @param listener
     * @return
     */
    public SaAlertDialog setPositiveButton(String text,
                                           final OnClickListener listener) {
        showPosBtn = true;
        if (TextUtils.isEmpty(text)) {
            btn_pos.setText("确定");
        } else {
            btn_pos.setText(text);
        }
        btn_pos.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                listener.onClick(v);
            }
        });
        return this;
    }

    /**
     * 确定按钮不带取消的diaglog
     *
     * @param text
     * @param listener
     * @return
     */
    public SaAlertDialog setConfirmButton(String text,
                                          final OnClickListener listener) {
        showPosBtn = true;
        if (TextUtils.isEmpty(text)) {
            btn_pos.setText("确定");
        } else {
            btn_pos.setText(text);
        }
        btn_pos.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
            }
        });
        return this;
    }

    public SaAlertDialog setNegativeButton(String text,
                                           final OnClickListener listener) {
        showNegBtn = true;
        if (TextUtils.isEmpty(text)) {
            btn_neg.setText("取消");
        } else {
            btn_neg.setText(text);
        }
        btn_neg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                listener.onClick(v);
            }
        });
        return this;
    }

    private void setLayout() {
        if (!showTitle && !showMsg) {
            txt_title.setText("提示");
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showTitle) {
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showMsg) {
            txt_msg.setVisibility(View.VISIBLE);
        }
        if (showContentView) {
            mFrameLayout.setVisibility(View.VISIBLE);
        } else {
            mFrameLayout.setVisibility(View.GONE);
        }
        if (!showPosBtn && !showNegBtn) {
            btn_pos.setText("确定");
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setBackgroundResource(R.drawable.bg_alertbutton_bottom);
            btn_pos.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        if (showPosBtn && showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setBackgroundResource(R.drawable.bg_alertbutton_right);
            btn_neg.setVisibility(View.VISIBLE);
            btn_neg.setBackgroundResource(R.drawable.bg_alertbutton_left);
            img_line.setVisibility(View.VISIBLE);
        }

        if (showPosBtn && !showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setBackgroundResource(R.drawable.bg_alertbutton_bottom);
        }

        if (!showPosBtn && showNegBtn) {
            btn_neg.setVisibility(View.VISIBLE);
            btn_neg.setBackgroundResource(R.drawable.bg_alertbutton_bottom);
        }

        if (!showPosBtn && !showNegBtn) {
            ll_btn_group.setVisibility(View.GONE);
        }
    }

    /**
     * 显示出对话框
     *
     * @return 返回对话框对象
     */
    public SaAlertDialog show() {
        try {
            setLayout();
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mSaAlertDialog;
    }


    /**
     * 供外部调用关闭对话框
     */
    public void dismiss() {
        try {
            dialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 对话框是否展示
     *
     * @return true-展示中，false-没有展示
     */
    public boolean isShowing() {
        if (dialog != null) {
            dialog.isShowing();
        }
        return false;
    }

    /**
     * 开启时间计时器，一般是调用接口成功后手动调用此方法进行倒计时开启操作
     */
    public void startTimer() {
        t = new Timer();
        tt = new TimerTask() {

            @Override
            public void run() {
                han.sendEmptyMessage(0x01);
            }
        };

        t.schedule(tt, 0, mCountDownInterval);
    }

    @SuppressLint("HandlerLeak")
    Handler han = new Handler() {
        public void handleMessage(android.os.Message msg) {
            mMillisInFuture -= mCountDownInterval;
            if (mMillisInFuture < 0) {
                dismiss();
                clearTimer();
            }
        }
    };

    /**
     * 释放资源
     */
    private void clearTimer() {
        if (tt != null) {
            tt.cancel();
            tt = null;
        }
        if (t != null)
            t.cancel();
        t = null;
    }

    /**
     * 和activity的onDestroy()方法同步
     */
    public void onDestroy() {
        clearTimer();
    }


    /**
     * 设置确定是否可以点击
     * @param clickable
     */
    public void setBtnPosClickable(boolean clickable) {
        btn_pos.setClickable(clickable);
    }

    /**
     * 设置取消是否可以点击`
     * @param clickable
     */
    public void setBtnNegClickable(boolean clickable) {
        btn_neg.setClickable(clickable);
    }

}
