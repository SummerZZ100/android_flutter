package cn.com.jsj.lib_base.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import cn.com.jsj.lib_base.R
import cn.com.jsj.lib_base.widget.CommonToolbar
import cn.com.jsj.lib_base.widget.net_progress_dialog.NetDialogFactory

/**
 * KotlinBase基类
 * @author : wangjing
 * @date : 2019/1/10
 */
abstract class BaseKTFragment : Fragment() {

    private var parentView: View? = null
    var mToolbar: CommonToolbar? = null
    var mToolBarBottomLine: View? = null
    var mContext: Context? = null
    var mActivity: Activity? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        parentView = inflater.inflate(getLayoutResId(), container, false)
        initToolBar()
        return parentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
        mActivity = activity
    }

    /**
     * 返回当前布局
     *
     * @return 布局
     */
    @LayoutRes
    abstract fun getLayoutResId(): Int

    open fun initToolbarView() {}

    private fun initToolBar() {
        mToolbar = parentView!!.findViewById(R.id.toolbar_common)
        mToolBarBottomLine =  parentView!!.findViewById(R.id.v_tool_bar_bottom_line)
        mToolbar?.setOnLeftIconClickListener { onToolbarLeftIconClick() }
        if (mToolbar != null) {
            initToolbarView()
        }
    }

    private fun onToolbarLeftIconClick() {
        mActivity!!.onBackPressed()
    }


    /*
     * 初始化视图层，具体调用逻辑自己实现
     */
    open fun initView() {}

    /**
     * 初始化数据层，具体调用逻辑自己实现
     */
    open fun initData() {}

    /**
     * 初始化视图层监听，具体调用逻辑自己实现
     */
    open fun initListener() {}

    open fun showNetDialog() {
        NetDialogFactory.showDialog(activity)
    }

    open fun dissmissDialog() {
        NetDialogFactory.dismissDialog()
    }

    override fun onPause() {
        super.onPause()
        NetDialogFactory.clearDialog()
    }



}