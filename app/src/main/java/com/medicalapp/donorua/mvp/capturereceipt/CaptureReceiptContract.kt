package com.medicalapp.donorua.mvp.capturereceipt

import android.content.ContentResolver
import android.net.Uri

interface CaptureReceiptContract {
    interface CaptureReceiptView {
        fun openOutputReceiptActivity(uri: Uri)
    }

    interface CaptureReceiptPresenter {
        fun onGotUri(uri: Uri)
    }
}