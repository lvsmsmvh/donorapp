package com.medicalapp.donorua.ui.check.capture

import android.app.Activity
import android.content.Intent
import android.net.Uri

interface CaptureReceiptContract {
    interface CaptureReceiptView {
        fun openOutputReceiptActivity(uri: Uri)
        fun getActivityInstance(): Activity
        fun disableClicks()
        fun enableClicks()
    }

    interface CaptureReceiptPresenter {
        fun requestCameraPermission()
        fun proceedDataFromActivityResult(requestCode: Int, data: Intent?)
        fun proceedDataFromRequestPermissions(requestCode: Int,
                                              permissions: Array<out String>,
                                              grantResults: IntArray)
        fun clickChoosePhoto()
        fun clickMakePhoto()
        fun onDestroy()
    }
}