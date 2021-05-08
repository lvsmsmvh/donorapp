package com.medicalapp.donorua.mvp.outputreceipt

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.medicalapp.donorua.R
import com.medicalapp.donorua.utils.LogTags
import kotlinx.android.synthetic.main.activity_output_receipt.*

class OutputReceiptActivity: AppCompatActivity(),
        IOutputReceiptContract.IOutputReceiptView
{

    private lateinit var presenter: OutputReceiptPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_output_receipt)

        presenter = OutputReceiptPresenter(this)
        Log.i(LogTags.TAG_PHOTO, "On create")
        presenter.onInit()
    }

    override fun getUri(): Uri = intent.data!!

    override fun outputText(text: String) {
        output_receipt_text.text = text
    }

    override fun getContext() = this

    override fun requestWritePermission() {
        Log.e(LogTags.TAG_PHOTO, "Req perm")
        presenter.onGotWritePermission()
        return
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_GRANTED) {
            presenter.onGotWritePermission()
            return
        }

        // Should we show an explanation?
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "Please, give us a writing permission to store AI files.",
                Toast.LENGTH_SHORT).show()
        }

        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                presenter.onGotWritePermission()
            }
        }
    }
}