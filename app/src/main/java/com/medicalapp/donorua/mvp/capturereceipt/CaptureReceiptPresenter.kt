package com.medicalapp.donorua.mvp.capturereceipt

import android.net.Uri

class CaptureReceiptPresenter(
    private val view: CaptureReceiptContract.CaptureReceiptView
): CaptureReceiptContract.CaptureReceiptPresenter {

    override fun onGotUri(uri: Uri) {
        view.openOutputReceiptActivity(uri)
    }
}