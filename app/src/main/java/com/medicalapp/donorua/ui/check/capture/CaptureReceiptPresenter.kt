package com.medicalapp.donorua.ui.check.capture

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.medicalapp.donorua.R
import kotlinx.android.synthetic.main.activity_capture_receipt.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CaptureReceiptPresenter(
    private val view: CaptureReceiptContract.CaptureReceiptView
): CaptureReceiptContract.CaptureReceiptPresenter {

    companion object {
        private const val TAG = "CameraXBasic"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val pickImageCode = 9
        private const val REQUEST_CODE_PERMISSIONS_CAMERA = 10
        private val REQUIRED_PERMISSIONS_CAMERA = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS_READ = 11
        private val REQUIRED_PERMISSIONS_READ = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService

    private var imageCapture: ImageCapture? = null

    private var activity = view.getActivityInstance()

    override fun requestCameraPermission() {
        if (cameraPermissionsGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                activity, REQUIRED_PERMISSIONS_CAMERA, REQUEST_CODE_PERMISSIONS_CAMERA
            )
        }

        outputDirectory = getOutputDirectory()

        cameraExecutor = Executors.newSingleThreadExecutor()
    }


    private fun openGalleryIfGranted(askForPermission: Boolean = false) {
        if (readPermissionsGranted()) {
            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            intent.type = "image/*"
            intent.putExtra("return-data", true)
            activity.startActivityForResult(intent, pickImageCode)
            return
        }

        if (askForPermission)
            ActivityCompat.requestPermissions(
                activity, REQUIRED_PERMISSIONS_READ, REQUEST_CODE_PERMISSIONS_READ
            )
    }


    private fun cameraPermissionsGranted() = REQUIRED_PERMISSIONS_CAMERA.all {
        ContextCompat.checkSelfPermission(
            activity.baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun readPermissionsGranted() = REQUIRED_PERMISSIONS_READ.all {
        ContextCompat.checkSelfPermission(
            activity.baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun getOutputDirectory(): File {
        val mediaDir = activity.externalMediaDirs.firstOrNull()?.let {
            File(it, activity.getString(R.string.app_name)).apply { mkdirs() } }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else activity.filesDir
    }

    override fun clickChoosePhoto() {
        openGalleryIfGranted(askForPermission = true)
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(activity)

        cameraProviderFuture.addListener({
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(activity.capture_receipt_preview_view.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder()
                .build()

            // Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    activity as LifecycleOwner, cameraSelector, preview, imageCapture)
//                cameraProvider.bindToLifecycle(
//                    activity as LifecycleOwner, cameraSelector, imageCapture)
            } catch(exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(activity))
    }

    override fun clickMakePhoto() {
        // Get a stable reference of the modifiable image capture use case
        val imageCapture = imageCapture ?: return

        view.disableClicks()

        // Create time-stamped output file to hold the image
        val photoFile = File(
            outputDirectory,
            SimpleDateFormat(
                FILENAME_FORMAT, Locale.US
            ).format(System.currentTimeMillis()) + ".jpg")

        // Create output options object which contains file + metadata
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        // Set up image capture listener, which is triggered after photo has
        // been taken
        imageCapture.takePicture(
            outputOptions, ContextCompat.getMainExecutor(activity), object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    view.enableClicks()
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    gotUri(uri = Uri.fromFile(photoFile))
                }
            })
    }

    override fun proceedDataFromActivityResult(requestCode: Int, data: Intent?) {
        if (requestCode != pickImageCode) return

        data?.data?.let { gotUri(uri = it) }
    }

    private fun gotUri(uri: Uri) {
        Log.i("tag_uri", "Got uri : " + uri.toString())
        view.openOutputReceiptActivity(uri)
        view.enableClicks()
    }

    override fun proceedDataFromRequestPermissions(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_PERMISSIONS_CAMERA -> {
                if (cameraPermissionsGranted()) {
                    startCamera()
                } else activity.finish()
            }
            REQUEST_CODE_PERMISSIONS_READ -> {
                openGalleryIfGranted(askForPermission = false)
            }
        }
    }

    override fun onDestroy() {
        cameraExecutor.shutdown()
    }
}