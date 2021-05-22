package com.medicalapp.donorua.mvp.outputreceipt


import android.net.Uri
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition


class OutputReceiptPresenter(
    private val view: IOutputReceiptContract.IOutputReceiptView
    ): IOutputReceiptContract.IOutputReceiptPresenter
{

    val context = view.getContext()

//    private val myDir = view.getContext().getExternalFilesDir(Environment.MEDIA_MOUNTED)
//    private val dataPath = myDir?.absolutePath + "/TesseractSample/"
//    private val tessdata = "tessdata"


    override fun gotUriFromExtras(uri: Uri) {
        output("Proceeding file")
        val image = InputImage.fromFilePath(context, uri)

        val recognizer = TextRecognition.getClient()
        output("Waiting for result...")

        val result = recognizer.process(image)
            .addOnSuccessListener { visionText ->
                gotTextFromImage(visionText)
            }
            .addOnFailureListener { e ->
                output("Failed with error: " + e.message)
                // Task failed with an exception
                // ...
            }
    }


    private fun gotTextFromImage(text: Text) {
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

        output(result)
    }

    private fun output(string: String) {
        view.outputText(string)
    }
}