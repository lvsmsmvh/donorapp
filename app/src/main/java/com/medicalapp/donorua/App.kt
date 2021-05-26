package com.medicalapp.donorua

import android.app.Application
import com.medicalapp.donorua.utils.helper.ChecksStorage
import com.medicalapp.donorua.utils.helper.DonorCentersStorage
import com.medicalapp.donorua.utils.helper.SharedPrefsHelper
import java.util.*


class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var sharedPrefs: SharedPrefsHelper
    lateinit var donorCenterStorage: DonorCentersStorage
    lateinit var checksStorage: ChecksStorage

    override fun onCreate() {
        super.onCreate()

        instance = this

        sharedPrefs = SharedPrefsHelper(this)

        donorCenterStorage = DonorCentersStorage(this)
        donorCenterStorage.restoreCenters()

        checksStorage = ChecksStorage(this)

        Locale.setDefault(Locale("uk", "UA"))
    }
}