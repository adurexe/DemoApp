package com.adurexe.lifecycle

import android.os.Bundle
import kotlinx.android.synthetic.main.fragment_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycle_tv.text("onCreate")
    }

    override fun onStart() {
        super.onStart()
        lifecycle_tv.text("onStart")
    }

    override fun onResume() {
        super.onResume()
        lifecycle_tv.text("onResume")
    }

    override fun onPause() {
        super.onPause()
        lifecycle_tv.text("onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle_tv.text("onDestroy")
    }

    override fun onStop() {
        super.onStop()
        lifecycle_tv.text("onStop")
    }
}
