package com.adyen.android.assignment.features.main_screen.domain.entities

import com.squareup.moshi.Json

data class AstronomyPicture(
    @Json(name = "service_version")
    val serviceVersion: String,
    val title: String,
    val explanation: String,
    val date: String,
    @Json(name = "media_type")
    val mediaType: String,
    @Json(name = "hdurl")
    val hdUrl: String? = null,
    val url: String,
)