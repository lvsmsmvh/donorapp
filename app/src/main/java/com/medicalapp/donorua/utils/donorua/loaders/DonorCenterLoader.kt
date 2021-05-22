package com.medicalapp.donorua.utils.donorua.loaders

import android.util.Log
import com.medicalapp.donorua.utils.Api
import com.medicalapp.donorua.utils.LogTags
import com.medicalapp.donorua.utils.donorua.DonorUaApi
import com.medicalapp.donorua.utils.donorua.jsoup.JsoupFeatures
import com.medicalapp.donorua.model.center.DonorCenter
import org.jsoup.nodes.Element

class DonorCenterLoader {
    fun load(
        donorCenter: DonorCenter
    ) : DonorCenter {
        val document = JsoupFeatures.getDocument(Api.URL_DOMAIN + donorCenter.urlInDonorUa)

        DonorUaApi.log(" Document title = " + document.title())

        val donorCenterElement = document
            .select("div[class=container mt-3 mb-3]")


        val leftRowElements = donorCenterElement
            .select("div[class=col-md-8]")

        val rightRowElementsTables = donorCenterElement
            .select("div[class=col-md-4]")
            .select("table[class=table]")

        Log.i(LogTags.TAG_API, "tables : " + rightRowElementsTables.size)

        val firstTable = rightRowElementsTables.eq(0)

//        val secondTable = rightRowElementsTables.eq(1) // for schedule

        val informationItemsElements = firstTable
            .select("tr")

        Log.i(LogTags.TAG_API, "information item elems : " + informationItemsElements.size)

        // title
        val title = leftRowElements
            .select("h1")
            .text()

        DonorUaApi.log(" Donor center  title (name) = " + document.title())


        // description
        val descriptionArticles = mutableListOf<String>()
        val articlesElements = leftRowElements
            .select("div[class=article]")
            .select("p")


        for (i in 0 until articlesElements.size) {
            val articleElement = articlesElements
                .eq(i)
            val article = articleElement
                .text()
            if (article.isNotEmpty()) {
                descriptionArticles.add(article)
            }
        }

        // rating
        var rating: Double? = null
        val possibleRatingElements = leftRowElements
            .select("p[class=text-muted]")
        possibleRatingElements.forEach {
            if (it.text().contains("Середній рейтинг")) {
                // TODO взять рейтинг, шаблон "Всього відгуків: 19. Середній рейтинг: 4,58"
                val text = it.text()
                rating = text.substring(text.length - 4, text.length).toDoubleOrNull()
            }
        }


        var linkOnGoogleMaps: String? = null
        var address: String? = null
        var email: String? = null
        var webPage: String? = null
        var instagram: String? = null
        var facebook: String? = null


        informationItemsElements.forEach { informationElement ->
            when (informationElement.getTypeOfInformationElement()) {
                InformationElementType.ADDRESS -> {
                    linkOnGoogleMaps = informationElement.takeAHref()
                    address = informationElement.takeAText()
                }
                InformationElementType.EMAIL -> {
                    email = informationElement.takeAHref().cutMailTo()
                }
                InformationElementType.WEB_PAGE -> {
                    webPage = informationElement.takeAHref()
                }
                InformationElementType.INSTAGRAM -> {
                    instagram = informationElement.takeAHref()
                }
                InformationElementType.FACEBOOK -> {
                    facebook = informationElement.takeAHref()
                }
            }
        }

        val idForDonorCenter = donorCenter.urlInDonorUa!!.substringAfterLast("/").toInt()

        return DonorCenter(
            id = idForDonorCenter,
            name = title,
            description = descriptionArticles,
            rating = rating,
            address = address,
            webPageUrl = webPage,
            email = email,
            linkOnGoogleMaps = linkOnGoogleMaps,
            instagram = instagram,
            facebook = facebook,
            cityName = donorCenter.cityName,
            regionName = donorCenter.regionName,
            urlInDonorUa = Api.URL_DOMAIN + donorCenter.urlInDonorUa
        )
    }


    enum class InformationElementType(
        val attrKey: String
    ) {
        ADDRESS("fa fa-location-arrow"),
        EMAIL("fa fa-envelope"),
        WEB_PAGE("fa fa-link"),
        INSTAGRAM("fab fa-instagram"),
        FACEBOOK("fab fa-facebook-f")
    }

    private fun Element.getTypeOfInformationElement()
    : InformationElementType?
    {
        val actualAttrKey = select("td[class=text-center]")
            .select("i")
            .attr("class")
        return InformationElementType.values().firstOrNull {
            it.attrKey == actualAttrKey
        }
    }


    private fun Element.takeAHref() =  select("a").attr("href")
    private fun Element.takeAText() =  select("a").text()

    private fun String.cutMailTo() = replace("mailto:", "")

}