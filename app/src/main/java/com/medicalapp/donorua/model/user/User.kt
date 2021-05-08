package com.medicalapp.donorua.model.user

import com.medicalapp.donorua.utils.extensions.defaultCalendarDate
import java.util.*

data class User(
    var name: String? = null,
    var surname: String? = null,
    var gender: Gender? = null,
    var bloodGroup: BloodGroup? = null,
    var birthDate: Calendar = defaultCalendarDate()
)