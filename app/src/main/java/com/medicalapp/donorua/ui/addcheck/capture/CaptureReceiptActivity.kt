package com.medicalapp.donorua.ui.addcheck.capture

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.medicalapp.donorua.R
import com.medicalapp.donorua.ui.addcheck.analyzer.ImageAnalyzerActivity
import com.medicalapp.donorua.utils.extensions.makeClickable
import com.medicalapp.donorua.utils.extensions.makeUnclickable
import kotlinx.android.synthetic.main.activity_capture_receipt.*

class CaptureReceiptActivity: AppCompatActivity(),
    CaptureReceiptContract.CaptureReceiptView,
        LifecycleOwner
{

    private lateinit var presenter: CaptureReceiptPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capture_receipt)

        presenter = CaptureReceiptPresenter(this)
        presenter.requestCameraPermission()

        initControl()
    }

    private fun initControl() {
        capture_receipt_button_choose_a_photo.setOnClickListener {
            presenter.clickChoosePhoto()
        }
        capture_receipt_button_make_a_photo.setOnClickListener {
            presenter.clickMakePhoto()
        }
    }

    override fun disableClicks() {
        capture_receipt_button_choose_a_photo.makeUnclickable()
        capture_receipt_button_make_a_photo.makeUnclickable()
    }

    override fun enableClicks() {
        capture_receipt_button_choose_a_photo.makeClickable()
        capture_receipt_button_make_a_photo.makeClickable()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.proceedDataFromActivityResult(requestCode, data)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        presenter.proceedDataFromRequestPermissions(requestCode, permissions, grantResults)
    }

    override fun getActivityInstance() = this

    override fun openOutputReceiptActivity(uri: Uri) {
        val intent = Intent(this, ImageAnalyzerActivity::class.java)
        intent.data = uri
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}