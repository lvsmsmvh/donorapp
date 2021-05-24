package com.medicalapp.donorua.utils.helper

import com.medicalapp.donorua.App
import com.medicalapp.donorua.model.center.DonorCenter

class FavoriteCenters(val app: App) {

    fun getList() = app.sharedPrefs.getListOfFavoriteCenters()
    fun saveList(list: List<DonorCenter>) = app.sharedPrefs.saveListOfFavoriteCenters(list)

    fun changeExistingOfACenter(donorCenter: DonorCenter) {
        if (isFavorite(donorCenter)) remove(donorCenter) else add(donorCenter)
    }

    private fun add(center: DonorCenter) {
        saveList(getList().toMutableSet().apply { add(center) }.toList())
    }

    private fun remove(center: DonorCenter) {
        saveList(getList().toMutableSet().apply { remove(center) }.toList())
    }

    fun isFavorite(center: DonorCenter) = getList().contains(center)
}