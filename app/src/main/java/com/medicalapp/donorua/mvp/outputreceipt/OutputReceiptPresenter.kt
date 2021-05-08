package com.medicalapp.donorua.mvp.outputreceipt


import android.app.Activity
import android.os.Environment
import android.util.Log
import com.medicalapp.donorua.utils.LogTags
import com.medicalapp.donorua.utils.extensions.toBitmap
import java.io.*


class OutputReceiptPresenter(
    private val view: IOutputReceiptContract.IOutputReceiptView
    ): IOutputReceiptContract.IOutputReceiptPresenter
{

    private val myDir = view.getContext().getExternalFilesDir(Environment.MEDIA_MOUNTED)
    private val dataPath = myDir?.absolutePath + "/TesseractSample/"
    private val tessdata = "tessdata"
    private val uri = view.getUri()
    override fun onGotWritePermission() {

    }

    override fun onInit() {
        Log.i(LogTags.TAG_PHOTO, "Please allow")
        view.outputText("Please, allow write permission.")
        view.requestWritePermission()
    }

}