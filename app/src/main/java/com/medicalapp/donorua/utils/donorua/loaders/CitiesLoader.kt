package com.medicalapp.donorua.utils.donorua.loaders

import com.medicalapp.donorua.utils.Api
import com.medicalapp.donorua.utils.donorua.jsoup.JsoupFeatures
import com.medicalapp.donorua.model.center.City
import com.medicalapp.donorua.model.center.DonorCenter
import com.medicalapp.donorua.model.center.Region
import org.jsoup.nodes.Document
import java.lang.Exception

class CitiesLoader {
    fun loadCitiesForRegion(
        region: Region
    ): List<City> {
        val listOfCities = mutableListOf<City>()

        val doc: Document

        try {
            doc = JsoupFeatures.getDocument(Api.URL_DOMAIN)
//            doc = JsoupFeatures.getDocument(Api.URL_DOMAIN + region.link)
        } catch (e: Exception) {
            return emptyList()
        }

        val listOfCitiesElements = doc
            .select("div[class=mt-3 mb-3]")

        for (i in 0 until listOfCitiesElements.size) {
            val cityElement = listOfCitiesElements
                .eq(i)

            val cityTitle = cityElement
                .select("h3")
                .text()

            val listOfCenters = mutableListOf<DonorCenter>()
            val listOfCentersElements = cityElement
                .select("p[class=text-muted]")

            for (j in 0 until listOfCentersElements.size) {
                val centerElement = listOfCentersElements
                    .eq(j)

                val centerTitle = centerElement
                    .select("a")
                    .text()

                val centerAddress = centerElement
                    .text()
                    .replace(centerTitle, "")       // cuz address copies title

                val centerLink = centerElement
                    .select("a")
                    .attr("href")

//                listOfCenters.add(
////                    DonorCenter(
////                        cityName = cityTitle,
////                        regionName = region.name,
////                        urlInDonorUa = centerLink
////                    )
//                )
            }

            listOfCities.add(
                City(
                    cityTitle,
                    listOfCenters
                )
            )
        }
        return listOfCities
    }
}