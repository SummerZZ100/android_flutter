package cn.com.jsj.lib_base.widget.picker;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import cn.com.jsj.lib_base.R;
import cn.com.jsj.lib_base.widget.picker.lib_picker.TimePicker;


/**
 * 时间选择器
 * Created by donghongyu on 16/3/19.
 */
public class SaTimePicker extends TimePicker {
    //取消按钮
    private Button mBtnCancel;
    //标题
    private TextView mTvTitle;
    //确定按钮
    private Button mBtnSubmit;

    public SaTimePicker(Activity activity) {
        super(activity);
    }

    public SaTimePicker(Activity activity, int mode) {
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
                dismiss();
                onSubmit();
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

}
