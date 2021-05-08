package com.medicalapp.donorua.utils.extensions

import android.content.ContentResolver
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import java.io.File

fun Uri.toBitmap(contentResolver: ContentResolver): Bitmap {
    return MediaStore.Images.Media.getBitmap(contentResolver, this)
}

fun Uri.toImageFile(contentResolver: ContentResolver): File? {
    val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
    val cursor = contentResolver
        .query(this, filePathColumn, null, null, null)
    if (cursor != null) {
        if (cursor.moveToFirst()) {
            val columnIndex = cursor.getColumnIndex(filePathColumn[0])
            val filePath = cursor.getString(columnIndex)
            cursor.close()
            return File(filePath)
        }
        cursor.close()
    }
    return null
}