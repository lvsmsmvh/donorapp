package com.medicalapp.donorua.utils.donorua.loaders

import com.medicalapp.donorua.utils.Api
import com.medicalapp.donorua.utils.donorua.jsoup.JsoupFeatures
import com.medicalapp.donorua.model.center.Region
import org.jsoup.nodes.Document
import java.lang.Exception

class RegionsLoader {
    fun load() : List<Region> {
        val listOfRegions = mutableListOf<Region>()

        val doc: Document
        try {
            doc = JsoupFeatures.getDocument(Api.URL_DOMAIN + Api.PAGE_HOME)
        } catch (e: Exception) {
            return emptyList()
        }

        val listOfRegionElements = doc
            .select("article")
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
                Region(title, emptyList())
//                Region(title, link)
            )
        }
        return listOfRegions
    }
}