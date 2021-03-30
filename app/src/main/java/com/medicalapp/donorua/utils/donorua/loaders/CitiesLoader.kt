package com.medicalapp.donorua.utils.donorua.loaders

import com.medicalapp.donorua.utils.Api
import com.medicalapp.donorua.utils.donorua.jsoup.JsoupFeatures
import com.medicalapp.donorua.utils.donorua.model.City
import com.medicalapp.donorua.utils.donorua.model.DonorCenterPreview
import com.medicalapp.donorua.utils.donorua.model.Region

class CitiesLoader {
    fun loadCitiesForRegion(
        region: Region
    ): List<City> {
        val listOfCities = mutableListOf<City>()
        val listOfCitiesElements = JsoupFeatures.getDocument(Api.URL_DOMAIN + region.link)
            .select("div[class=mt-3 mb-3]")

        for (i in 0 until listOfCitiesElements.size) {
            val cityElement = listOfCitiesElements
                .eq(i)

            val title = cityElement
                .select("h3")
                .text()

            val listOfCenters = mutableListOf<DonorCenterPreview>()
            val listOfCentersElements = cityElement
                .select("p[class=text-muted]")

            for (j in 0 until listOfCentersElements.size) {
                val centerElement = listOfCentersElements
                    .eq(j)

                val centerTitle = centerElement
                    .select("a")
                    .text()

                val centerAddress = centerElement
                    .select("br")
                    .text()

                val centerLink = centerElement
                    .select("a")
                    .attr("href")

                listOfCenters.add(
                    DonorCenterPreview(
                        centerTitle,
                        centerAddress,
                        centerLink
                    )
                )
            }

            listOfCities.add(
                City(
                    title,
                    listOfCenters
                )
            )
        }
        return listOfCities
    }
}