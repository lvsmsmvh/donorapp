package com.medicalapp.donorua.mvp.model.user

import com.medicalapp.donorua.mvp.model.geo.PlaceInfo
import java.util.*

data class User(
    var name: String,
    var surname: String,
    var gender: Gender,
    val bloodGroup: BloodGroup,
    val birthDate: Calendar,
    var placeGeo: PlaceInfo? = null
)