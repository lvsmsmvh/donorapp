package com.medicalapp.donorua.utils.helper

import com.medicalapp.donorua.App
import com.medicalapp.donorua.model.center.DonorCenter

class FavoriteCenters(val app: App) {

    fun getList() = app.sharedPrefs.getListOfFavoriteCenters()
    fun saveList(list: List<DonorCenter>) = app.sharedPrefs.saveListOfFavoriteCenters(list)

    fun changeExisting(donorCenter: DonorCenter) {
        if (isFavorite(donorCenter)) remove(donorCenter) else add(donorCenter)
    }

    fun add(center: DonorCenter) {
        saveList(getList().toMutableSet().apply { add(center) }.toList())
    }

    fun remove(center: DonorCenter) {
        saveList(getList().toMutableSet().apply { remove(center) }.toList())
    }

    fun isFavorite(center: DonorCenter) = getList().contains(center)
}