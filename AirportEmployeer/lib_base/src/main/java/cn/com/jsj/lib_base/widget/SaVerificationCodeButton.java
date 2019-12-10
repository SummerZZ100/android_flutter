package cn.com.jsj.lib_base.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;

import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.widget.AppCompatButton;
import cn.com.jsj.lib_base.R;


/**
 * 点击获取验证码的按钮视图，完成倒计时文案自动更新，以及自身点击事件的失去与获取
 *
 * @author donghongyu
 * @date 2016/5/25
 */
public class SaVerificationCodeButton extends AppCompatButton {

    /**
     * 记录倒计时总时长
     */
    private int mCountTimeLenght = 60000;

    /**
     * 倒计时总时长，默认60000毫秒=60秒
     */
    private int mMillisInFuture = 60000;


    /**
     * 倒计时间隔时常，默认1000毫秒=1秒
     */
    private int mCountDownInterval = 1000;


    /**
     * 点击之前的控件文案，默认“获取验证码”
     */
    private String mClickBeforeTxt = "获取验证码";

    /**
     * 点击之后，倒计时完成文案。默认“重新获取”
     */
    private String mCountDownTxt = "后可重新获取";

    private Timer t;

    private TimerTask tt;

    public SaVerificationCodeButton(Context context) {
        this(context, null);
    }

    public SaVerificationCodeButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SaVerificationCodeButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化倒计时控需要的数据
        initDate(context, attrs);
        initViewDate();
    }

    /**
     * 初始化数据
     */
    private void initDate(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SaVerificationCodeButton);
        mCountTimeLenght = a.getInt(R.styleable.SaVerificationCodeButton_millis_in_future, 60000);
        mMillisInFuture = mCountTimeLenght;
        mCountDownInterval = a.getInt(R.styleable.SaVerificationCodeButton_count_down_interval, 1000);
        //获取文案
        mClickBeforeTxt = a.getString(R.styleable.SaVerificationCodeButton_click_before_txt);
        if (mClickBeforeTxt == null) {
            mClickBeforeTxt = "获取验证码";
        }
        mCountDownTxt = a.getString(R.styleable.SaVerificationCodeButton_count_down_txt);
        if (mCountDownTxt == null) {
            mCountDownTxt = "后可重新获取";
        }
        a.recycle();
    }


    /**
     * 初始化控件数据
     */
    private void initViewDate() {
        setText(mClickBeforeTxt);
    }


    /**
     * 开启时间计时器，一般是调用接口成功后手动调用此方法进行倒计时开启操作
     */
    public void startTimer() {
        mMillisInFuture = mCountTimeLenght;
        t = new Timer();
        tt = new TimerTask() {

            @Override
            public void run() {
                han.sendEmptyMessage(0x01);
            }
        };

        //设置文字展示，以及取消控件的可点击状态
        setText(mMillisInFuture / 1000 + "s" + mCountDownTxt);
        setEnabled(false);
        t.schedule(tt, 0, mCountDownInterval);
    }

    @SuppressLint("HandlerLeak")
    Handler han = new Handler() {
        public void handleMessage(android.os.Message msg) {
            SaVerificationCodeButton.this.setText(mMillisInFuture / 1000 + "s" + mCountDownTxt);
            mMillisInFuture -= mCountDownInterval;
            if (mMillisInFuture < 0) {
                SaVerificationCodeButton.this.setEnabled(true);
                SaVerificationCodeButton.this.setText("重新获取");
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

}
