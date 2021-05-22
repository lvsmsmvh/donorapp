package com.medicalapp.donorua.utils.donorua

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Looper
import android.util.Log
import com.medicalapp.donorua.utils.Api
import com.medicalapp.donorua.utils.LogTags
import com.medicalapp.donorua.utils.donorua.loaders.CitiesLoader
import com.medicalapp.donorua.utils.donorua.loaders.DonorCenterLoader
import com.medicalapp.donorua.utils.donorua.loaders.RegionsLoader
import com.medicalapp.donorua.utils.donorua.model.City
import com.medicalapp.donorua.utils.donorua.model.DonorCenter
import com.medicalapp.donorua.utils.donorua.model.DonorCenterPreview
import com.medicalapp.donorua.utils.donorua.model.Region

class DonorUaApi(private val activity: Activity) {

    fun loadAllCenters(callback: (List<DonorCenter>) -> Unit) {
        val listOfDonorCenters = mutableListOf<DonorCenter>()
        log("Start load")
        logSpaceDivider()
        loadListOfRegionLinks { listOfRegions ->
            log("List of regions loaded : $listOfRegions")
            logSpaceDivider()
            listOfRegions.forEachIndexed { _, region ->
//                if (index > 1) return@forEachIndexed
                loadListOfCitiesWithDonorCenters(region) { listOfCities ->
                    log("List of cities loaded : $listOfCities")
                    logSpaceDivider()
                    listOfCities.forEach { city ->
                        city.centers.forEach { donorCenter ->
                            logSpaceDivider()
                            loadDonorCenter(donorCenter) {
//                                log("Donor center loaded : $it")
                                listOfDonorCenters.add(it)
                            }
                        }
                    }
                }
            }
        }

        android.os.Handler(Looper.getMainLooper()).postDelayed({
            val listOfCityNames = listOfDonorCenters.map { it.id }
            log(listOfCityNames.toString())
        }, 180_000)
    }



    fun loadListOfRegionLinks(
        callback: (List<Region>) -> Unit
    ) {
        Thread {
            val result = RegionsLoader().load()
            activity.runOnUiThread {
                callback(result)
            }
        }.start()
    }



    fun loadListOfCitiesWithDonorCenters(
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
        donorCenter: DonorCenter,
        callback: (DonorCenter) -> Unit
    ) {
        Thread {
            val result = DonorCenterLoader().load(donorCenter)
            activity.runOnUiThread {
                callback(result)
            }
        }.start()
    }


    fun openDonorPreviewInWeb(donorCenterPreview: DonorCenterPreview) {
        val link = Api.URL_DOMAIN + donorCenterPreview.link
        val browserIntent = Intent(Intent.ACTION_VIEW,
            Uri.parse(link))
        activity.startActivity(browserIntent)
    }
    companion object {
        fun log(str: String) {
            Log.i(LogTags.TAG_API, str)
        }

        fun logSpaceDivider() {
            repeat(3) {
                Log.i(LogTags.TAG_API, "\n*************************" +
                        "*****************************\n")
            }
        }
    }
}
