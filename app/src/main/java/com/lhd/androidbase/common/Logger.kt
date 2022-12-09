package com.lhd.androidbase.common

import android.util.Log
import com.lhd.androidbase.BuildConfig

object Logger {

    fun log(tag: String, message: String) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, message)
        }
    }

}