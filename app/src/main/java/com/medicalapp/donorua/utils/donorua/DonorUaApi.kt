package com.medicalapp.donorua.utils.donorua

import android.app.Activity
import android.util.Log
import com.medicalapp.donorua.utils.LogTags
import com.medicalapp.donorua.utils.donorua.loaders.CitiesLoader
import com.medicalapp.donorua.utils.donorua.loaders.DonorCenterLoader
import com.medicalapp.donorua.utils.donorua.loaders.RegionsLoader
import com.medicalapp.donorua.utils.donorua.model.Region
import com.medicalapp.donorua.utils.donorua.model.City
import com.medicalapp.donorua.utils.donorua.model.DonorCenter
import com.medicalapp.donorua.utils.donorua.model.DonorCenterPreview

class DonorUaApi(private val activity: Activity) {




    fun loadListOfRegions(
        callback: (List<Region>) -> Unit
    ) {
        Thread {
            val result = RegionsLoader().load()
            activity.runOnUiThread {
                callback(result)
            }
        }.start()
    }



    fun loadCitiesForRegion(
        region: Region,
        callback: (List<City>) -> Unit
    ) {
        Thread {
            val result = CitiesLoader().loadCitiesForRegion(region)
            activity.runOnUiThread {
                callback(result)
            }
        }.start()
    }




    fun loadDonorCenter(
        donorCenterPreview: DonorCenterPreview,
        callback: (DonorCenter) -> Unit
    ) {
        Thread {
            val result = DonorCenterLoader().load(donorCenterPreview)
            activity.runOnUiThread {
                callback(result)
            }
        }.start()
    }


    private fun log(str: String) {
        Log.i(LogTags.TAG_API, str)
    }
}