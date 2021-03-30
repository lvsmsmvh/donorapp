package com.medicalapp.donorua.utils.donorua.jsoup

import com.medicalapp.donorua.utils.Api
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

object JsoupFeatures {
    fun getDocument(url: String): Document {
        return Jsoup
            .connect(url)
            .maxBodySize(0)
            .timeout(Api.MAX_TIMEOUT_LOADING)
            .get()
    }
}