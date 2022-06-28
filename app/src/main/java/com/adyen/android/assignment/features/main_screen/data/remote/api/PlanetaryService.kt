package com.adyen.android.assignment.features.main_screen.data.remote.api

import com.adyen.android.assignment.features.main_screen.domain.entities.AstronomyPicture
import retrofit2.Response
import retrofit2.http.GET

interface PlanetaryService {

    @GET("planetary/apod?count=20")
    suspend fun getPictures(): Response<List<AstronomyPicture>>

}
