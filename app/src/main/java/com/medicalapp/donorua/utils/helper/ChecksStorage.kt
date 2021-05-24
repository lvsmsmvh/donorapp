package com.medicalapp.donorua.utils.helper

import com.medicalapp.donorua.App
import com.medicalapp.donorua.model.check.Check

class ChecksStorage(val app: App) {

    private fun getList() = app.sharedPrefs.getListOfChecks()
    private fun saveList(list: List<Check>) = app.sharedPrefs.saveListOfChecks(list)


    fun add(check: Check) {
        saveList(getList().toMutableSet().apply { add(check) }.toList())
    }

    fun remove(check: Check) {
        saveList(getList().toMutableSet().apply { remove(check) }.toList())
    }

    fun isInTheList(check: Check) = getList().contains(check)
}