package com.medicalapp.donorua.utils.helper

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.medicalapp.donorua.App
import com.medicalapp.donorua.model.center.DonorCenter

class DonorCentersStorage(val app: App) {

    val favoriteCenters = FavoriteCentersStorage(app)

    var listOfDonorCenter: List<DonorCenter> = emptyList()
        get() {
            return if (field.isNotEmpty())
                field
            else
                app.sharedPrefs.getListOfCenters()
        }

    fun restoreCenters() {
        if (listOfDonorCenter.isNotEmpty()) return

        // Start loading from database
        Firebase.database.reference.child("centers").get()
            .addOnSuccessListener {
                val list = it.children.map { dataSnapshot ->
                    dataSnapshot.getValue(DonorCenter::class.java)!!
                }

                listOfDonorCenter = list
                app.sharedPrefs.saveListOfCenters(list)
            }
    }


    fun getCenterById(id: Int) = listOfDonorCenter.first { it.id == id }
}

