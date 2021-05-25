package com.medicalapp.donorua.model.notification

import java.util.*

class NotificationVisit(
    val visitTime: Calendar,
    val periodBeforeSending: PeriodBeforeSending,
    val withSound: Boolean
)