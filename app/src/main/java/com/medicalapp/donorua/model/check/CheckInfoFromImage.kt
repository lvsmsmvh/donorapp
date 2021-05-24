package com.medicalapp.donorua.model.check

import android.net.Uri
import java.util.*

data class CheckInfoFromImage(
    val uriOnImage: Uri,
    val dateAndTime: Calendar
)