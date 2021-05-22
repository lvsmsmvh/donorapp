package com.medicalapp.donorua.model.center

data class DonorCenter(
    val id: Int? = null,
    val urlInDonorUa: String? = null,
    val cityName: String? = null,
    val regionName: String? = null,
    val name: String? = null,
    val description: List<String>? = null,
    val rating: Double? = null,
    val address: String? = null,
    val webPageUrl: String? = null,
    val email: String? = null,
    val linkOnGoogleMaps: String? = null,
    val instagram: String? = null,
    val facebook: String? = null,
)