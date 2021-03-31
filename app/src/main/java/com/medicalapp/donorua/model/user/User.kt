package com.medicalapp.donorua.model.user

import java.util.*

data class User(
    var name: String,
    var surname: String,
    var gender: Gender,
    val bloodGroup: BloodGroup,
    val birthDate: Calendar
)