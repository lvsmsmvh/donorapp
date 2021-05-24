package com.medicalapp.donorua.utils.extensions

import com.google.gson.Gson
import com.medicalapp.donorua.model.check.Check

fun Check.toJson() = Gson().toJson(this)

fun String.toCheckClass() = Gson().fromJson(this, Check::class.java)