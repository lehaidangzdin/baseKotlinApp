package com.lhd.androidbase.base.activities

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.lhd.androidbase.base.dialogs.ConfirmDialog
import com.lhd.androidbase.base.dialogs.ErrorDialog
import com.lhd.androidbase.base.dialogs.LoadingDialog
import com.lhd.androidbase.base.dialogs.NotifyDialog

open class BaseActivity : AppCompatActivity() {
    private var loadingDialog: LoadingDialog? = null


    open fun showLoading(isShow: Boolean) {
        if (isShow) {
            loadingDialog = LoadingDialog(this)
            loadingDialog?.show()
            loadingDialog?.window?.setGravity(Gravity.CENTER)
            loadingDialog?.window?.setLayout(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
//            loadingDialog?.window?.setElevation(0f)
            loadingDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        } else {
            loadingDialog?.let {
                if (it.isShowing)
                    it.dismiss()
            }
        }
    }

    open fun showErrorDialog(message: String) {
        val errorDialog = ErrorDialog(this, message)
        errorDialog.show()
        errorDialog.window?.setGravity(Gravity.CENTER)
        errorDialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    open fun showNotifyDialog(
        titleResourceId: Int,
        messageResourceId: Int,
        textButtonResourceId: Int = -1
    ) {
        val title = getString(titleResourceId)
        val message = getString(messageResourceId)
        val textButton = if (textButtonResourceId == -1) null else getString(textButtonResourceId)
        showNotifyDialog(message, title, textButton)
    }

    open fun showNotifyDialog(message: String, title: String, textButton: String? = null) {
        val notifyDialog = NotifyDialog(this, title, message, textButton)
        notifyDialog.show()
        notifyDialog.window?.setGravity(Gravity.CENTER)
        notifyDialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

//    open fun showConfirmDialog(
//        titleResourceId: Int,
//        messageResourceId: Int = -1,
//        positiveTitleResourceId: Int,
//        negativeTitleResourceId: Int,
//        textButtonResourceId: Int = -1,
//        posCallback: () -> Unit,
//        nevCallback: () -> Unit
//    ) {
//        val title = getString(titleResourceId)
//        val message = if (messageResourceId != -1) getString(messageResourceId) else null
//        val negativeButtonTitle = getString(negativeTitleResourceId)
//        val positiveButtonTitle = getString(positiveTitleResourceId)
//        val textButton = if (textButtonResourceId == -1) null else getString(textButtonResourceId)
//
//        showConfirmDialog(
//            title,
//            message,
//            negativeButtonTitle,
//            positiveButtonTitle,
//            posCallback,
//            nevCallback,
//        )
//    }

    open fun showConfirmDialog(
        title: String,
        message: String?,
        positiveButtonTitle: String,
        negativeButtonTitle: String,
        posCallback: () -> Unit,
        nevCallback: () -> Unit,
    ) {
        val confirmDialog = ConfirmDialog(
            context = this,
            title = title,
            message = message,
            positiveButtonTitle = positiveButtonTitle,
            negativeButtonTitle = negativeButtonTitle,
            posCallback = posCallback,
            nevCallback = nevCallback
        )
        confirmDialog.show()
        confirmDialog.window?.setGravity(Gravity.CENTER)
        confirmDialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
}