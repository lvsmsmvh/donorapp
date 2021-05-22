package com.medicalapp.donorua.utils.extensions

import com.medicalapp.donorua.model.center.City
import com.medicalapp.donorua.model.center.DonorCenter
import com.medicalapp.donorua.model.center.Region

fun List<DonorCenter>.toListOfCities(): List<City>  {
    val mapOfCities = mutableMapOf<String, MutableList<DonorCenter>>()

    forEach { donorCenter ->
        val cityName = donorCenter.cityName!!
        if (mapOfCities[cityName] == null)
            mapOfCities[cityName] = mutableListOf()
        mapOfCities[cityName]?.add(donorCenter)
    }

    return mapOfCities.map {
        City(
            name = it.key,
            centers = it.value
        )
    }
}

fun List<DonorCenter>.toListOfRegions(): List<Region>  {
    val listOfCities = toListOfCities()

    val mapOfRegions = mutableMapOf<String, MutableList<City>>()

    listOfCities.forEach { city ->
        val regionName = city.centers[0].regionName!!
        if (mapOfRegions[regionName] == null)
            mapOfRegions[regionName] = mutableListOf()
        mapOfRegions[regionName]!!.add(city)
    }

    return mapOfRegions.map {
        Region(
            name = it.key,
            cities = it.value
        )
    }
}
