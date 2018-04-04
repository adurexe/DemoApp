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
        text("create")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        text("start")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun resume() {
        text("resume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pause() {
        text("pause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        text("stop")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy() {
        text("destroy")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun any() {
        text("any")
    }

    fun setLifecycleEanable(boolean: Boolean) {
        enable = boolean
        if (mlifecycle != null) {
            if (mlifecycle?.currentState?.isAtLeast(STARTED)!!) {
            }
        }
    }

    open fun text(msg: String) {
        if (enable) {
            buf?.append(System.currentTimeMillis().toString(), "-", msg, "\n")
            this.text = buf
        }
    }
}