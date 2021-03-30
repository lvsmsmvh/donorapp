package com.medicalapp.donorua.mvp.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.medicalapp.donorua.utils.LogTags
import com.medicalapp.donorua.utils.donorua.DonorUaApi

class FindCityActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val donorUaApi = DonorUaApi(this)
        donorUaApi.loadListOfRegions { listRegion ->
            Log.i(LogTags.TAG_API, "CALLBACK 1 " + listRegion.toString())
            donorUaApi.loadCitiesForRegion(listRegion[0]) { listCity ->
                Log.i(LogTags.TAG_API, "CALLBACK 2 " + listCity.toString())
                donorUaApi.loadDonorCenter(listCity[0].centers[0]) { listDonorCenter ->
                    Log.i(LogTags.TAG_API, "CALLBACK 3 " + listDonorCenter.toString())
                }
            }
        }
    }

}