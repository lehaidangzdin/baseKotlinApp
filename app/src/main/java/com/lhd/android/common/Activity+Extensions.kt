package com.lhd.android.common

import android.app.Activity
import com.lhd.android.CustomApplication

val Activity.customApplication: CustomApplication
get() = application as CustomApplication