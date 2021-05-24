package com.medicalapp.donorua.utils.extensions

import java.util.*

fun Calendar.getDateInStringFormat() =
            get(Calendar.DAY_OF_MONTH).toString() + "/" +
            get(Calendar.MONTH).toString() + "/" +
            get(Calendar.YEAR)

fun Calendar.getTimeInStringFormat() =
    get(Calendar.HOUR_OF_DAY).toString() + ":" + get(Calendar.MINUTE).toString()

fun Calendar.defaultDate(): Calendar {
    set(Calendar.YEAR, get(Calendar.YEAR) - 20)
    return this
}

fun defaultCalendarDate() = Calendar.getInstance().apply {
    set(Calendar.YEAR, 2000)
    set(Calendar.MONTH, 1)
    set(Calendar.DAY_OF_MONTH, 1)
} // 01.01.2000

fun Calendar.isOlderThanEighteen(): Boolean {
    val calendarEighteenYearsAgo = Calendar.getInstance().apply {
        set(Calendar.YEAR, get(Calendar.YEAR) - 18)
    }
    return timeInMillis < calendarEighteenYearsAgo.timeInMillis
}

fun Calendar.getPersonAgeInYears(): Int {
    val calendarNow = Calendar.getInstance()
    val birthDate = this

    val calendarNowYear = calendarNow.get(Calendar.YEAR)
    val birthDateYear = birthDate.get(Calendar.YEAR)

    var yearsDiffBetweenCalendars = calendarNowYear - birthDateYear

    calendarNow.set(Calendar.YEAR, birthDateYear)
    if (calendarNow.timeInMillis < birthDate.timeInMillis) {
        yearsDiffBetweenCalendars--
    }

    return yearsDiffBetweenCalendars
}