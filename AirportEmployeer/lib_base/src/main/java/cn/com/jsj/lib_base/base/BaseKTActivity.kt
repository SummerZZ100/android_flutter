package cn.com.jsj.lib_base.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import cn.com.jsj.lib_base.R
import cn.com.jsj.lib_base.widget.CommonToolbar
import cn.com.jsj.lib_base.widget.net_progress_dialog.NetDialogFactory
import com.gyf.barlibrary.ImmersionBar

/**
 * BaseActivity 使用Kotlin 语言
 * @author : wangjing
 * @date : 2018/12/28
 */
abstract class BaseKTActivity : AppCompatActivity() {
    var mToolbar: CommonToolbar? = null
    var mToolBarBottomLine: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initImmersionBar()
        BaseKTLifecycle(lifecycle)
        initToolBar()
        initView()
        initListener()
        initData()
    }

    open fun initImmersionBar() {
        ImmersionBar.with(this).statusBarDarkFont(true, 0.2f).init()
    }

    private fun initToolBar() {
        mToolbar = findViewById(R.id.toolbar_common)
        mToolBarBottomLine = findViewById(R.id.v_tool_bar_bottom_line)
        if (mToolbar != null) {
            mToolbar!!.setOnLeftIconClickListener {
                onToolbarLeftIconClick()
            }
            initToolBarView()
        }
    }

    open fun initToolBarView() {}

    abstract fun getLayoutId(): Int
    open fun initView() {}
    open fun initListener() {}
    open fun initData() {}


    open fun onToolbarLeftIconClick() {
        onBackPressed()
    }

    open fun showNetDialog() {
        NetDialogFactory.showDialog(this)
    }

    open fun dissmissDialog() {
        NetDialogFactory.dismissDialog()
    }

    override fun onPause() {
        super.onPause()
        NetDialogFactory.clearDialog()
    }

}