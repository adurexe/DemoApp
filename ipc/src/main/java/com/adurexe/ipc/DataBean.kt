package com.adurexe.ipc

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by pactera on 2018/4/26.
 */
@Parcelize
@SuppressLint("ParcelCreator")
data class DataBean(val id: String, val name: String, val grade: String) : Parcelable