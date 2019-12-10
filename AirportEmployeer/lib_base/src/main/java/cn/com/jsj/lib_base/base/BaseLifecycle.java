package cn.com.jsj.lib_base.base;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * Lifecycle基类，已经默认和
 *
 * @author : wangjing
 * @date : 2018/12/13
 */
public  class BaseLifecycle implements LifecycleObserver {


    public BaseLifecycle(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate(){}

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart(){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume(){

    }


    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause(){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop(){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(){
    }

}
