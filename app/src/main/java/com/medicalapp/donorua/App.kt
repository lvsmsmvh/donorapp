package com.medicalapp.donorua

import android.app.Application
import android.util.Log
import com.medicalapp.donorua.utils.LogTags.TAG_CHIP
import com.medicalapp.donorua.utils.helper.SharedPrefsHelper


class App : Application() {
    companion object {
        lateinit var instance: App
    }


    lateinit var sharedPrefs: SharedPrefsHelper


    override fun onCreate() {
        super.onCreate()
        Log.i(TAG_CHIP, "app on create")
        instance = this

        sharedPrefs = SharedPrefsHelper(this)

        Log.i(TAG_CHIP, "app on create finish")

    }
}