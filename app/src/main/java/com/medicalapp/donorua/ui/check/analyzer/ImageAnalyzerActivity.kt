package com.medicalapp.donorua.ui.check.analyzer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.medicalapp.donorua.R
import com.medicalapp.donorua.model.check.Check
import com.medicalapp.donorua.ui.check.addinfo.EditCheckActivity
import com.medicalapp.donorua.ui.check.capture.CaptureReceiptActivity
import com.medicalapp.donorua.utils.Parameters
import com.medicalapp.donorua.utils.extensions.*
import kotlinx.android.synthetic.main.activity_output_receipt.*
import java.util.*

class ImageAnalyzerActivity: AppCompatActivity() {

    private lateinit var uri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_output_receipt)

        uri = intent.data!!

        startAnalyzingImage(uri)
    }

    private fun startAnalyzingImage(uri: Uri) {
        val image = InputImage.fromFilePath(this, uri)

        TextRecognition.getClient().process(image)
            .addOnSuccessListener { visionText ->
                startParsingCheckInfo(visionText)
            }
            .addOnFailureListener { e ->
                showError()
            }
    }

    private fun startParsingCheckInfo(text: Text) {
//        var result = ""
//        for (block in text.textBlocks) {
//            val blockText = block.text
//            result += "\n\n\nblock \n $blockText"
//
//            val blockCornerPoints = block.cornerPoints
//            val blockFrame = block.boundingBox
//            for (line in block.lines) {
//                val lineText = line.text
//                val lineCornerPoints = line.cornerPoints
//                val lineFrame = line.boundingBox
//                for (element in line.elements) {
//                    val elementText = element.text
//                    val elementCornerPoints = element.cornerPoints
//                    val elementFrame = element.boundingBox
//                }
//            }
//        }
//
//        outputText(result)

        val string = text.text

        val dateStr = string.findDate()
        val timeStr = string.findTime()

        if (dateStr != null && timeStr != null) {
            openNextActivityWithCheck(check = Check(
                uriOnImage = uri.toString(),
                dateAndTime = Calendar.getInstance().applyNewDateAndTime(dateStr, timeStr)
            ))
            return
        }

        // did not success to parse text :(

        if (Parameters.isAcceptingAllChecks) {
            openNextActivityWithCheck(check = Check(
                uriOnImage = uri.toString(),
                dateAndTime = Calendar.getInstance()
            ))
            return
        }

        showError()
    }

    private fun showError() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Помилка")
            .setMessage("Не вдалося обробити зображення. Спробуйте перефотографувати чек ще раз.")
            .setPositiveButton(
                R.string.OK
            ) { dialog, _ ->
                openPreviousActivity()
            }
            .setOnDismissListener {
                openPreviousActivity()
            }
            .show()
    }

    private fun openPreviousActivity() {
        val intent = Intent(this, CaptureReceiptActivity::class.java)

        startActivity(intent)
        finish()
    }

    private fun openNextActivityWithCheck(check: Check) {
        val intent = Intent(this, EditCheckActivity::class.java)
        intent.putExtra(EditCheckActivity.KEY_CHECK, check.toJson())

        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
//        super.onBackPressed()
    }
}