package com.medicalapp.donorua.ui.addcheck.analyzer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.medicalapp.donorua.R
import com.medicalapp.donorua.model.check.Check
import com.medicalapp.donorua.ui.addcheck.addinfo.AddCheckActivity
import com.medicalapp.donorua.utils.extensions.findDate
import com.medicalapp.donorua.utils.extensions.findTime
import com.medicalapp.donorua.utils.extensions.toJson
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

        outputText("Waiting for result...")

        TextRecognition.getClient().process(image)
            .addOnSuccessListener { visionText ->
                startParsingCheckInfo(visionText)
            }
            .addOnFailureListener { e ->
                outputText("Failed with error: " + e.message)
            }
    }

    private fun startParsingCheckInfo(text: Text) {
        var result = ""
        for (block in text.textBlocks) {
            val blockText = block.text
            result += "\n\n\nblock \n $blockText"

            val blockCornerPoints = block.cornerPoints
            val blockFrame = block.boundingBox
            for (line in block.lines) {
                val lineText = line.text
                val lineCornerPoints = line.cornerPoints
                val lineFrame = line.boundingBox
                for (element in line.elements) {
                    val elementText = element.text
                    val elementCornerPoints = element.cornerPoints
                    val elementFrame = element.boundingBox
                }
            }
        }

        outputText(result)

        val string = text.text

        val dateStr = string.findDate()
        val timeStr = string.findTime()

        Log.i("tag_analyzer", "date $dateStr , time $timeStr")
        val checkJson = Check(
            uriOnImage = uri.toString(),
            dateAndTime = Calendar.getInstance()
        ).toJson()

        val intent = Intent(this, AddCheckActivity::class.java)
        intent.putExtra(AddCheckActivity.KEY_CHECK, checkJson)

        startActivity(intent)
        finish()
    }

    private fun outputText(text: String) {
        output_receipt_text.text = text
    }
}