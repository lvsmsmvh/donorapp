package com.medicalapp.donorua.ui.outputreceipt

import android.content.Context
import android.net.Uri

interface IOutputReceiptContract {
    interface IOutputReceiptView {
        fun outputText(text: String)
        fun getContext(): Context
//        fun requestWritePermission()
    }

    interface IOutputReceiptPresenter {
//        fun onGotWritePermission()
        fun gotUriFromExtras(uri: Uri)
    }
}