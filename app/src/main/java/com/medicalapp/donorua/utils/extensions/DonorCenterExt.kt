package com.medicalapp.donorua.utils.extensions

import com.medicalapp.donorua.utils.donorua.model.City
import com.medicalapp.donorua.utils.donorua.model.DonorCenter

fun List<DonorCenter>.toListOfCity(): List<City>  {
    val mapOfCities = mutableMapOf<String, MutableList<DonorCenter>>()

    forEach { donorCenter ->
        if (mapOfCities[donorCenter.cityName] == null)
            mapOfCities[donorCenter.cityName!!] = mutableListOf()
        mapOfCities[donorCenter.cityName]?.add(donorCenter)
    }

    return mapOfCities.map { City(it.key, it.value) }
}
