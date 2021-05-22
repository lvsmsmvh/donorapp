package com.medicalapp.donorua.model.check

import android.net.Uri
import java.util.*

data class Check(
    val uri: Uri,
    val dateAndTime: Calendar
)