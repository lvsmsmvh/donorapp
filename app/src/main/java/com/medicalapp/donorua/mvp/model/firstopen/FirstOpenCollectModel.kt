package com.medicalapp.donorua.mvp.model.firstopen

import com.medicalapp.donorua.mvp.model.user.BloodGroup
import com.medicalapp.donorua.mvp.model.user.Gender
import java.util.*

class FirstOpenCollectModel(
    val name: String?,
    val surname: String?,
    val gender: Gender?,
    val bloodGroup: BloodGroup?,
    val birthDate: Calendar
)