package com.adurexe.livedata

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_livedata.*

/**
 * Created by pactera on 2018/4/17.
 */
class LiveDataFragment : LifecycleFragment() {

    private var viewModel: LiveDataViewModel? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_livedata, container, true)
    }


    private fun getObserver() = Observer<DataBean> { dataBean ->
        textview.text = dataBean!!.string
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LiveDataViewModel::class.java)
        viewModel?.dataBean?.observe(this, getObserver())
    }
}
