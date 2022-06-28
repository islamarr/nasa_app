package com.adyen.android.assignment.features.main_screen.domain.repositories

import com.adyen.android.assignment.common.data.NetworkResponse
import com.adyen.android.assignment.common.data.NetworkServiceCallHandler
import com.adyen.android.assignment.features.main_screen.domain.entities.AstronomyPicture

interface PlanetaryRepository : NetworkServiceCallHandler{
    suspend fun getPictures(): NetworkResponse<List<AstronomyPicture>>
}