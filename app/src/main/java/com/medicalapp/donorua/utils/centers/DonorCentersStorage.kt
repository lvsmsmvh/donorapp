package com.medicalapp.donorua.utils.centers

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.medicalapp.donorua.App
import com.medicalapp.donorua.utils.donorua.model.DonorCenter

class DonorCentersStorage(val app: App) {

    var listOfDonorCenter: List<DonorCenter>? = null

    fun restoreCenters() {
        // Centers already in variable
        if (listOfDonorCenter != null) {
            return
        }

        // Centers were in preferences
        app.sharedPrefs.getListOfCenters()?.let { list ->
            listOfDonorCenter = list
            return
        }

        // Start loading from database
        Firebase.database.reference.child("centers").get()
            .addOnSuccessListener {
                val list = it.children.map { dataSnapshot ->
                    dataSnapshot.getValue(DonorCenter::class.java)!!
                }

                if (list.isNotEmpty()) {
                    listOfDonorCenter = list
                    app.sharedPrefs.saveListOfCenters(list)
                }
            }
    }
}