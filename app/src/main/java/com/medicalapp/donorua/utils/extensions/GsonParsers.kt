package com.medicalapp.donorua.utils.extensions

import com.google.gson.Gson
import com.medicalapp.donorua.model.check.Check
import com.medicalapp.donorua.model.notification.NotificationVisit

fun Check.toJson(): String = Gson().toJson(this)
fun String.toCheckClass(): Check =
    Gson().fromJson(this, Check::class.java)


fun NotificationVisit.toJson(): String = Gson().toJson(this)
fun String.toNotificationVisit(): NotificationVisit =
    Gson().fromJson(this, NotificationVisit::class.java)