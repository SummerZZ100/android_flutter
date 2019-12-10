package com.jsj.airport

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.annotation.CallSuper
import cn.com.jsj.lib_base.base.BaseKTApplication
import cn.com.jsj.lib_common.netcore.NetHelper
import cn.com.jsj.lib_common.netcore.retrofit.RetrofitWorker
import cn.jpush.android.api.JPushInterface
import com.idlefish.flutterboost.NewFlutterBoost
import com.idlefish.flutterboost.Utils
import com.idlefish.flutterboost.interfaces.INativeRouter
import com.jsj.airport.flutter.base.AirportFlutterEngine
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import io.flutter.embedding.android.FlutterView
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.PluginRegistry
import io.flutter.plugins.GeneratedPluginRegistrant

/**
 * Kotlin  版本的baseApplication
 * @author : wangjing
 * @date : 2018/12/28
 */
class MyApplication : Application() {

    //单例模式
    companion object {
        private lateinit var instance: MyApplication
        fun getInstance(): MyApplication {
            return instance
        }
    }


    @CallSuper
    override fun onCreate() {
        super.onCreate()

        instance = this
        //初始化flutter引擎
        var engine = AirportFlutterEngine()
        engine.initMyFlutterEngine()
    }

}