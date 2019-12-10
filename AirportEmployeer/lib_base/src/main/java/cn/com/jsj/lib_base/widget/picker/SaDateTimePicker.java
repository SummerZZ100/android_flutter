package cn.com.jsj.lib_base.widget.picker;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

import androidx.annotation.Nullable;
import cn.com.jsj.lib_base.R;
import cn.com.jsj.lib_base.widget.picker.lib_picker.DateTimePicker;


/**
 * Created by donghongyu on 16/4/14.
 */
public class SaDateTimePicker extends DateTimePicker {
    //取消按钮
    private Button mBtnCancel;
    //标题
    private TextView mTvTitle;
    //确定按钮
    private Button mBtnSubmit;
    //设置标记 true：选定的日期需要判断 false：选定的日期不需要判断
    private boolean isfilter = false; //默认不需要

    public SaDateTimePicker(Activity activity) {
        super(activity);
    }

    public SaDateTimePicker(Activity activity, int mode) {
        super(activity, mode);
    }

    public SaDateTimePicker(Activity activity, int mode, int time_mode) {
        super(activity, mode, time_mode);
    }

    @Nullable
    @Override
    protected View makeHeaderView() {
        View view = LayoutInflater.from(activity).inflate(R.layout.picker_header, null);

        mBtnCancel = (Button) view.findViewById(R.id.btn_cancel);
        mTvTitle = (TextView) view.findViewById(R.id.tv_title);
        mBtnSubmit = (Button) view.findViewById(R.id.btn_submit);
        //设置监听
        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isfilter) {
                    dismiss();
                    onSubmit();
                } else {
                    onSubmit();
                }
            }
        });
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                onCancel();
            }
        });
        return view;
    }

    /**
     * 设置标题
     *
     * @param strTitle 标题
     */
    public void setTitle(String strTitle) {
        if (null != mTvTitle) {
            mTvTitle.setText(strTitle);
        }
    }

    public void setIsfilter(boolean is) {
        isfilter = is;
    }


    /**
     * 定位到当前时间进行选中
     */
    public void selectNowTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        String nowYear = String.valueOf( calendar.get(Calendar.YEAR));
        String nowMonth = String.valueOf( calendar.get(Calendar.MONTH) + 1);
        String nowDate = String.valueOf( calendar.get(Calendar.DATE));

        String nowHour = String.valueOf( calendar.get(Calendar.HOUR));
        String nowMinute = String.valueOf( calendar.get(Calendar.MINUTE) + 1);
        setSelectedItem(nowYear, nowMonth, nowDate, nowHour, nowMinute);
    }
}
