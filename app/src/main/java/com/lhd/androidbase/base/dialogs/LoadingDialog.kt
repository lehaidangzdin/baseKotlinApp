package com.lhd.androidbase.base.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.lhd.androidbase.R

class LoadingDialog(context: Context) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        val rootView = layoutInflater.inflate(R.layout.dialog_loading, null, false)
        setContentView(rootView)

    }
}