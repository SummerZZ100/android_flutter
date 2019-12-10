package com.jsj.airport.flutter.base;


import android.content.Context;
import android.util.Log;

import com.idlefish.flutterboost.FlutterBoostPlugin;
import com.idlefish.flutterboost.NewFlutterBoost;
import com.idlefish.flutterboost.Platform;
import com.idlefish.flutterboost.interfaces.INativeRouter;
import com.jsj.airport.MyApplication;

import java.util.Map;

import io.flutter.embedding.android.FlutterView;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

/*
* 员工端与Flutter引擎交互
* */
public class AirportFlutterEngine {

    public void des(){
        NewFlutterBoost.instance().boostDestroy();
    }


    /*
    * 初始化Flutter引擎
    * */
    public void initMyFlutterEngine(){


        INativeRouter router = new INativeRouter() {
            //打开flutter第一个页面时的回调
            @Override
            public void openContainer(Context context, String url, Map<String, Object> urlParams, int requestCode, Map<String, Object> exts) {

                Log.e("FLUTTER_ENGINE","openContainer");

            }
        };

        NewFlutterBoost.BoostLifecycleListener lifecycleListener = new NewFlutterBoost.BoostLifecycleListener(){
            //Flutter引擎的创建回调方法
            @Override
            public void onEngineCreated() {
                Log.e("FLUTTER_ENGINE","onEngineCreated");

            }

            //Flutter插件加载时的回调方法
            @Override
            public void onPluginsRegistered() {

                Log.e("FLUTTER_ENGINE","onPluginsRegistered");

            }

            //flutter引擎销毁回调
            @Override
            public void onEngineDestroy() {

//                NewFlutterBoost.instance().boostDestroy();
//                Log.e("FLUTTER_ENGINE","onEngineDestroy");

            }
        };


        //注册flutter引擎
        Platform platform = new NewFlutterBoost.ConfigBuilder(MyApplication.Companion.getInstance(),router)
                .isDebug(true)
                .whenEngineStart(NewFlutterBoost.ConfigBuilder.ANY_ACTIVITY_CREATED)
                .renderMode(FlutterView.RenderMode.texture)
                .lifecycleListener(lifecycleListener)
                .build();



        NewFlutterBoost.instance().init(platform);




    }


}
