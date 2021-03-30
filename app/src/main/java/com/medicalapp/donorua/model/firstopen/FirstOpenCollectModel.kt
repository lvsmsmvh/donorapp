package com.medicalapp.donorua.model.firstopen

import com.medicalapp.donorua.model.user.BloodGroup
import com.medicalapp.donorua.model.user.Gender
import java.util.*

class FirstOpenCollectModel(
    val name: String?,
    val surname: String?,
    val gender: Gender?,
    val bloodGroup: BloodGroup?,
    val birthDate: Calendar
)