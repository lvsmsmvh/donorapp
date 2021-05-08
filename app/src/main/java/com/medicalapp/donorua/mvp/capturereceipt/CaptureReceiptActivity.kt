package com.medicalapp.donorua.mvp.capturereceipt

import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import com.medicalapp.donorua.R
import com.medicalapp.donorua.mvp.outputreceipt.OutputReceiptActivity
import com.medicalapp.donorua.utils.extensions.simpleNavigate
import kotlinx.android.synthetic.main.activity_capture_receipt.*
import java.io.File


class CaptureReceiptActivity: AppCompatActivity(), CaptureReceiptContract.CaptureReceiptView {

    private var pickImageCode = 10

    private lateinit var presenter: CaptureReceiptPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capture_receipt)

        presenter = CaptureReceiptPresenter(this)

        initControl()
    }

    private fun initControl() {
        capture_receipt_button_choose_a_photo.setOnClickListener {
            pickImage()
        }
    }

    private fun pickImage() {
        val intent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.INTERNAL_CONTENT_URI
        )
        intent.type = "image/*"
//        intent.putExtra("crop", "true")
//        intent.putExtra("scale", true)
//        intent.putExtra("outputX", 256)
//        intent.putExtra("outputY", 256)
//        intent.putExtra("aspectX", 1)
//        intent.putExtra("aspectY", 1)
        intent.putExtra("return-data", true)
        startActivityForResult(intent, pickImageCode)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == pickImageCode) {
            data?.data?.let {
                presenter.onGotUri(it)
            }
        }
    }

    override fun openOutputReceiptActivity(uri: Uri) {
        val intent = Intent(this, OutputReceiptActivity::class.java)
        intent.data = uri
        startActivity(intent)
    }
}