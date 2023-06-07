package com.lhd.androidbase.common

import android.app.Activity
import com.lhd.androidbase.CustomApplication

val Activity.customApplication: CustomApplication
get() = application as CustomApplication