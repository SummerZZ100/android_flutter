package cn.com.jsj.lib_base.base

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import me.jessyan.autosize.AutoSizeConfig
import me.jessyan.autosize.unit.Subunits

/**
 * Kotlin 版本的BaseApplication
 * @author : wangjing
 * @date : 2018/12/28
 */
abstract class BaseKTApplication : MultiDexApplication() {

    //单例模式
    companion object {
        private lateinit var instance: BaseKTApplication
        fun getInstance(): BaseKTApplication {
            return instance
        }
    }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initAutoSizeConfig()
        initCommon()
    }


    /**
     * 初始化今日头条屏幕适配方案AutoSize相关配置
     */
    private fun initAutoSizeConfig() {
        //支持Fragment 支持dp和sp ，副单位为【MM】，可用于新版本.由于当前采用了支持dp、sp,所以附单位暂不使用
        AutoSizeConfig.getInstance().isCustomFragment = true
        AutoSizeConfig.getInstance().unitsManager.isSupportDP = true
        AutoSizeConfig.getInstance().unitsManager.isSupportSP = true
        AutoSizeConfig.getInstance().unitsManager.supportSubunits = Subunits.MM
    }

    abstract fun initCommon()

}