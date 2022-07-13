package com.adyen.android.assignment.features.main_screen.data.repositories

import com.adyen.android.assignment.common.AppCoroutineDispatchers
import com.adyen.android.assignment.common.data.NetworkResponse
import com.adyen.android.assignment.features.main_screen.data.remote.api.PlanetaryService
import com.adyen.android.assignment.features.main_screen.domain.entities.AstronomyPicture
import com.adyen.android.assignment.features.main_screen.domain.repositories.PlanetaryRepository
import javax.inject.Inject

class PlanetaryRepositoryImpl @Inject constructor(
    private val dispatchers: AppCoroutineDispatchers,
    private val planetaryService: PlanetaryService
) : PlanetaryRepository {
    override suspend fun getPictures(): NetworkResponse<List<AstronomyPicture>> =
        safeApiCall(dispatchers.io) {
            planetaryService.getPictures()
        }
}