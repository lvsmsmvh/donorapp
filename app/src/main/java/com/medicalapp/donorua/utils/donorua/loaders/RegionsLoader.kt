package com.medicalapp.donorua.utils.donorua.loaders

import com.medicalapp.donorua.utils.Api
import com.medicalapp.donorua.utils.donorua.jsoup.JsoupFeatures
import com.medicalapp.donorua.utils.donorua.model.Region

class RegionsLoader {
    fun load() : List<Region> {
        val listOfRegions = mutableListOf<Region>()

        val listOfRegionElements = JsoupFeatures.getDocument(Api.URL_DOMAIN + Api.PAGE_HOME)
            .select("div[class=row]")
            .select("p")

        for (i in 0 until listOfRegionElements.size) {
            val cityElement = listOfRegionElements
                .eq(i)

            val title = cityElement
                .select("a")
                .text()

            val link = cityElement
                .select("a")
                .attr("href")

            listOfRegions.add(
                Region(
                    title,
                    link
                )
            )
        }
        return listOfRegions
    }
}