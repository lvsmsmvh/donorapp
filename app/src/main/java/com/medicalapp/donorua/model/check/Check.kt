package com.medicalapp.donorua.model.check

import com.medicalapp.donorua.model.center.DonorCenter
import java.util.*

data class Check(
    val uriOnImage: String,
    val dateAndTime: Calendar,
    var center: DonorCenter? = null
)