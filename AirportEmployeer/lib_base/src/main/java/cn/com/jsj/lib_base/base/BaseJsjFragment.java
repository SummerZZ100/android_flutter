package cn.com.jsj.lib_base.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import cn.com.jsj.lib_base.R;
import cn.com.jsj.lib_base.widget.CommonToolbar;
import cn.com.jsj.lib_base.widget.net_progress_dialog.NetDialogFactory;

/**
 * Fragment基类
 *
 * @author : wangjing
 * @date : 2018/12/24
 */
public abstract class BaseJsjFragment extends Fragment {
    protected View parentView;
    public CommonToolbar mToolbar;
    public Context mContext;
    public Activity mActivity;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        parentView = inflater.inflate(getLayoutResId(), container, false);
        initToolBar();
        initView();
        initListener();
        return parentView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
        mActivity = getActivity();
    }

    /**
     * 返回当前布局
     *
     * @return 布局
     */
    @LayoutRes
    public abstract int getLayoutResId();

    public abstract void initToolbarView();

    protected void initToolBar() {
        mToolbar = parentView.findViewById(R.id.toolbar_common);
        if (mToolbar == null) {
            return;
        }
        mToolbar.setOnLeftIconClickListener(new CommonToolbar.OnLeftIconClickListener() {
            @Override
            public void onLeftIconClick() {
                onToolbarLeftIconClick();
            }
        });
        initToolbarView();
    }

    public void onToolbarLeftIconClick() {
        mActivity.onBackPressed();
    }


    /**
     * 初始化视图层，具体调用逻辑自己实现
     */
    protected abstract void initView();

    /**
     * 初始化数据层，具体调用逻辑自己实现
     */
    protected abstract void initData();

    /**
     * 初始化视图层监听，具体调用逻辑自己实现
     */
    protected abstract void initListener();
    public void showNetDialog() {
        NetDialogFactory.showDialog(mActivity);
    }

    public void dissmissDialog() {
        NetDialogFactory.dismissDialog();
    }

    @Override
    public void onPause() {
        super.onPause();
        NetDialogFactory.clearDialog();
    }





}
