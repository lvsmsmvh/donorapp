package com.medicalapp.donorua.utils.extensions

import android.util.Log
import com.medicalapp.donorua.utils.LogTags.TAG_CALENDAR
import java.util.*

fun Calendar.setTwentyYearsAgo(): Calendar {
    set(Calendar.YEAR, get(Calendar.YEAR) - 20)
    return this
}

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



private fun Calendar.logCalendar() {
    Log.i(TAG_CALENDAR,
        "\n\nYears : " + get(Calendar.YEAR) +
        "\nMonth : " + get(Calendar.MONTH) +
        "\nDay : " + get(Calendar.DAY_OF_MONTH)
    )
}