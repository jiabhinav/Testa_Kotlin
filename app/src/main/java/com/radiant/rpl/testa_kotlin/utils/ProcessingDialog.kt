package com.radiant.rpl.testa_kotlin.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.KeyEvent
import com.radiant.rpl.testa_kotlin.R
import com.radiant.rpl.testa_kotlin.application.App

object ProcessingDialog {
    private var mDialog: Dialog? = null
        fun showProgressDialog(context: Activity) {
                mDialog = Dialog(context)
                mDialog?.setContentView(R.layout.progress_bar_layout)
                mDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                mDialog!!.setCancelable(false)
                if (!(context).isFinishing) {
                    if (!mDialog!!.isShowing) {
                        mDialog!!.show()
                    }
                }
                mDialog!!.setOnCancelListener { mDialog!!.dismiss() }
                mDialog!!.setOnKeyListener { dialog, keyCode, event ->
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        mDialog!!.dismiss()
                    }
                    true
                }

        }
        fun dismiss() {
            if (mDialog != null) {
                mDialog!!.dismiss()
            }
        }


}