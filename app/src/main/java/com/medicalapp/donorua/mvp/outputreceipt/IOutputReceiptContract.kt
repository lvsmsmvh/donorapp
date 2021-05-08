package com.medicalapp.donorua.mvp.outputreceipt

import android.content.Context
import android.net.Uri

interface IOutputReceiptContract {
    interface IOutputReceiptView {
        fun getUri(): Uri
        fun outputText(text: String)
        fun getContext(): Context
        fun requestWritePermission()
    }

    interface IOutputReceiptPresenter {
        fun onGotWritePermission()
        fun onInit()
    }
}