package com.medicalapp.donorua.model.notification

enum class PeriodBeforeSending(
    val millis: Long, val text: String
    ) {
    THIRTY_MINUTES(30 * 1000L, "30 хвилин"),
    ONE_HOUR(60 * 1000L, "1 година"),
    TWO_HOURS(120 * 1000L, "2 години"),
    SIX_HOURS(360 * 1000L, "6 годин"),
}