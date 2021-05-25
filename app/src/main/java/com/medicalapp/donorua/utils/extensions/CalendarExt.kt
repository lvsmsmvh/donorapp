package com.medicalapp.donorua.utils.extensions

import java.util.*
import kotlin.coroutines.cancellation.CancellationException

fun Long.toCalendar() = Calendar.getInstance().apply {
    timeInMillis = this@toCalendar
}

// format 12/12/2000
fun Calendar.getDateInStringFormat() =
            get(Calendar.DAY_OF_MONTH).toString() + "/" +
            get(Calendar.MONTH).toString() + "/" +
            get(Calendar.YEAR)

// format 22:00
fun Calendar.getTimeInStringFormat(): String {
    var hour = get(Calendar.HOUR_OF_DAY).toString()
    var minute = get(Calendar.MINUTE).toString()

    if (hour.length == 1) hour = "0$hour"
    if (minute.length == 1) minute = "0$minute"
    return "$hour:$minute"
}


// date format 11/11/2011 , time format 23:59
fun Calendar.applyNewDateAndTime(date: String, time: String): Calendar {
    val intArrayDate = date.split("/").map { it.toInt() }

    set(Calendar.DAY_OF_MONTH, intArrayDate[0])
    set(Calendar.MONTH, intArrayDate[1])
    set(Calendar.YEAR, intArrayDate[2])

    val timeArrayDate = time.split(":"). map { it.toInt() }

    set(Calendar.HOUR_OF_DAY, timeArrayDate[0])
    set(Calendar.MINUTE, timeArrayDate[1])

    return this
}

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

fun Calendar.hasSameYearMonthDayHourMinuteWithAnotherCalendar(calendar: Calendar) =
            get(Calendar.YEAR) == calendar.get(Calendar.YEAR) &&
            get(Calendar.MONTH) == calendar.get(Calendar.MONTH) &&
            get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH) &&
            get(Calendar.HOUR_OF_DAY) == calendar.get(Calendar.HOUR_OF_DAY) &&
            get(Calendar.MINUTE) == calendar.get(Calendar.MINUTE)


