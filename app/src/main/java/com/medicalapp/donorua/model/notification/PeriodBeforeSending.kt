package com.medicalapp.donorua.model.notification

enum class PeriodBeforeSending(
    val millis: Long, val text: String
    ) {
//    THREE_MINUTES(3 * 60 * 1000L, "3 хвилини"),
    ONE_HOUR(60 * 60 * 1000L, "1 година"),
    TWO_HOURS(2 * 60 * 60 * 1000L, "2 години"),
    SIX_HOURS(6 * 60 * 60 * 1000L, "6 годин"),
    TWELVE_HOURS(12 * 60 * 60 * 1000L, "12 годин"),
}