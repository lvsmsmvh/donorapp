package com.medicalapp.donorua.utils.helper

import android.util.Log
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.medicalapp.donorua.App
import com.medicalapp.donorua.model.center.DonorCenter

class DonorCentersStorage(val app: App) {

    val favoriteCenters = FavoriteCenters(app)

    var listOfDonorCenter: List<DonorCenter>? = null

    fun restoreCenters() {
        // Centers already in variable
        if (listOfDonorCenter != null) {
            logStorage("donor centers already in variable")
            return
        }

        logStorage("pref: start checking")
        // Centers were in preferences
        app.sharedPrefs.getListOfCenters()?.let { list ->
            logStorage("pref: restored")
            listOfDonorCenter = list
            return
        }
        logStorage("pref: not found")


        logStorage("database: start")

        // Start loading from database
        Firebase.database.reference.child("centers").get()
            .addOnSuccessListener {
                val list = it.children.map { dataSnapshot ->
                    dataSnapshot.getValue(DonorCenter::class.java)!!
                }

                logStorage("database: success")

                if (list.isNotEmpty()) {
                    listOfDonorCenter = list
                    app.sharedPrefs.saveListOfCenters(list)
                }
                logStorage("database: saved to pref")
            }
    }


    fun getCenterById(id: Int) = listOfDonorCenter!!.first { it.id == id }

    companion object {
        fun logStorage(string: String) {
            Log.i("tag_storage", string + "")
        }
    }
}

