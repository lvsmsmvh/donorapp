package com.medicalapp.donorua.utils.donorua.model

data class DonorCenter(
    val name: String,
    val description: List<String>,
    val rating: Float?,
    val address: String?,
    val webPageUrl: String?,
    val phoneNumbers: List<String>,
    val email: String?,
    val linkOnGoogleMaps: String?,
    val instagram: String?,
    val facebook: String?,
    //    val weekSchedule: WeekSchedule
)