package cn.com.jsj.lib_base.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * todo 描述：模块名_具体页面描述
 * @author : wangjing
 * @date : 2018/12/28
 */
open class BaseKTLifecycle(var lifecycle: Lifecycle): LifecycleObserver {
    init {
        lifecycle.addObserver(this)
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreate() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {

    }


    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
    }


}