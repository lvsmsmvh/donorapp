package com.medicalapp.donorua

import android.app.Application
import com.medicalapp.donorua.utils.helper.SharedPrefsHelper


class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var sharedPrefs: SharedPrefsHelper

    override fun onCreate() {
        super.onCreate()

        instance = this

        sharedPrefs = SharedPrefsHelper(this)
    }

}