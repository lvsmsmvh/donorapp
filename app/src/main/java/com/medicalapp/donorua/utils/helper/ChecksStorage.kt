package com.medicalapp.donorua.utils.helper

import com.medicalapp.donorua.App
import com.medicalapp.donorua.model.check.Check
import com.medicalapp.donorua.utils.extensions.hasSameYearMonthDayHourMinuteWithAnotherCalendar

class ChecksStorage(private val app: App) {

    fun getList() = app.sharedPrefs.getListOfChecks()
    private fun saveList(list: List<Check>) = app.sharedPrefs.saveListOfChecks(list)


    fun add(check: Check) {
        saveList(getList().toMutableSet().apply { add(check) }.toList())
    }

    fun remove(check: Check) {
        saveList(getList().toMutableSet().apply { remove(check) }.toList())
    }

    fun isInTheList(check: Check) = containsElementWithSameDate(check)

    private fun containsElementWithSameDate(check: Check) = getList().firstOrNull {
        it.dateAndTime.hasSameYearMonthDayHourMinuteWithAnotherCalendar(check.dateAndTime)
    } != null

}