package cn.com.jsj.lib_base.widget.picker;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import cn.com.jsj.lib_base.R;
import cn.com.jsj.lib_base.widget.picker.lib_picker.MonthDayWeekPicker;

/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 * 2016/8/10	19:17	    刘泽			    新增 类
 * 2016/8/10	19:17	    刘泽			    月日星期在一起的选择器,  分钟以10为一个单位
 */
public class SaMonthDayWeekPicker extends MonthDayWeekPicker {
    //取消按钮
    private Button mBtnCancel;
    //标题
    private TextView mTvTitle;
    //确定按钮
    private Button mBtnSubmit;
    //设置标记 true：选定的日期需要判断 false：选定的日期不需要判断
    private boolean isfilter = false; //默认不需要

    public SaMonthDayWeekPicker(Activity activity) {
        this(activity,MONTH_DAY_HOUR_MINUTE);
    }

    public SaMonthDayWeekPicker(Activity activity, int mode) {
        super(activity, mode);
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
}
