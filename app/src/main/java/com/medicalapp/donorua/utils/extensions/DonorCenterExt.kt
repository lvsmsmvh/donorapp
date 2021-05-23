package com.medicalapp.donorua.utils.extensions

import com.medicalapp.donorua.model.center.City
import com.medicalapp.donorua.model.center.DonorCenter
import com.medicalapp.donorua.model.center.Region
import com.medicalapp.donorua.utils.ukrainianAlphabet
import java.util.*
import kotlin.Comparator

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

fun Region.numberOfCenters(): Int {
    var numberOfCenters = 0
    cities.forEach { city ->
        numberOfCenters += city.centers.size
    }
    return numberOfCenters
}

fun City.numberOfCenters() = centers.size.toString()

fun List<Region>.sortRegionsByAlphabet() = sortedWith(Comparator { r1: Region, r2: Region ->
    if (r1.name.isEmpty() || r2.name.isEmpty()) return@Comparator -1
    val firstLetterComparing = compareValues(r1.name[0].index(), r2.name[0].index())
    if (firstLetterComparing != 0) return@Comparator firstLetterComparing

    if (r1.name.length == 1 || r2.name.length == 1) return@Comparator -1
    val secondLetterComparing = compareValues(r1.name[1].index(), r2.name[1].index())
    if (secondLetterComparing != 0) return@Comparator secondLetterComparing

    if (r1.name.length == 2 || r2.name.length == 2) return@Comparator -1
    val thirdLetterComparing = compareValues(r1.name[2].index(), r2.name[2].index())
    if (thirdLetterComparing != 0) return@Comparator thirdLetterComparing

    return@Comparator -1
})

fun List<City>.sortCityByAlphabet() = sortedWith(Comparator { r1: City, r2: City ->
    if (r1.name.isEmpty() || r2.name.isEmpty()) return@Comparator -1
    val firstLetterComparing = compareValues(r1.name[0].index(), r2.name[0].index())
    if (firstLetterComparing != 0) return@Comparator firstLetterComparing

    if (r1.name.length == 1 || r2.name.length == 1) return@Comparator -1
    val secondLetterComparing = compareValues(r1.name[1].index(), r2.name[1].index())
    if (secondLetterComparing != 0) return@Comparator secondLetterComparing

    if (r1.name.length == 2 || r2.name.length == 2) return@Comparator -1
    val thirdLetterComparing = compareValues(r1.name[2].index(), r2.name[2].index())
    if (thirdLetterComparing != 0) return@Comparator thirdLetterComparing

    return@Comparator -1
})

fun List<DonorCenter>.sortCentersByAlphabet() = sortedWith(Comparator { r1: DonorCenter, r2: DonorCenter ->
    r1.name!!
    r2.name!!
    if (r1.name.isEmpty() || r2.name.isEmpty()) return@Comparator -1
    val firstLetterComparing = compareValues(r1.name[0].index(), r2.name[0].index())
    if (firstLetterComparing != 0) return@Comparator firstLetterComparing

    if (r1.name.length == 1 || r2.name.length == 1) return@Comparator -1
    val secondLetterComparing = compareValues(r1.name[1].index(), r2.name[1].index())
    if (secondLetterComparing != 0) return@Comparator secondLetterComparing

    if (r1.name.length == 2 || r2.name.length == 2) return@Comparator -1
    val thirdLetterComparing = compareValues(r1.name[2].index(), r2.name[2].index())
    if (thirdLetterComparing != 0) return@Comparator thirdLetterComparing

    return@Comparator -1
})

fun Char.index() = ukrainianAlphabet.indexOf(this)