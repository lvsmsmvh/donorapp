package com.medicalapp.donorua.model.check

import android.net.Uri
import com.medicalapp.donorua.model.center.DonorCenter
import java.util.*

data class Check(
    val uriOnImage: Uri,
    val dateAndTime: Calendar,
    val center: DonorCenter
)