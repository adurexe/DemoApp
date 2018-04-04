package com.adurexe.lifecycle

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.Lifecycle.State.STARTED
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet

/**
 * Created by pactera on 2018/3/30.
 */

class LifecycleTextView : AppCompatTextView, LifecycleObserver {
    var buf: StringBuffer? = StringBuffer()
    var mlifecycle: Lifecycle? = null
    var enable: Boolean = true

    constructor(context: Context) : super(context) {
        init()
    }


    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    fun init() {
        buf = StringBuffer()
    }

    fun setLifecycle(lifecycle: Lifecycle) {
        mlifecycle = lifecycle
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun create() {
        if (enable) {
            buf?.append(System.currentTimeMillis().toString(), "-creat", "\n")
            this.text = buf
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        if (enable) {
            buf?.append(System.currentTimeMillis().toString(), "-start", "\n")
            this.text = buf
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun resume() {
        if (enable) {
            buf?.append(System.currentTimeMillis().toString(), "-resume", "\n")
            this.text = buf
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pause() {
        if (enable) {
            buf?.append(System.currentTimeMillis().toString(), "-pause", "\n")
            this.text = buf
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        if (enable) {
            buf?.append(System.currentTimeMillis().toString(), "-stop", "\n")
            this.text = buf
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy() {
        if (enable) {
            buf?.append(System.currentTimeMillis().toString(), "-destroy", "\n")
            this.text = buf
        }
    }

    fun setLifecycleEanable(boolean: Boolean) {
        enable = boolean
        if (mlifecycle != null) {
            if (mlifecycle?.currentState?.isAtLeast(STARTED)!!) {
            }
        }
    }
}