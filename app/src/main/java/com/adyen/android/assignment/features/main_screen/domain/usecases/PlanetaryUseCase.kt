package com.adyen.android.assignment.features.main_screen.domain.usecases

import com.adyen.android.assignment.common.data.NetworkResponse
import com.adyen.android.assignment.features.main_screen.domain.repositories.PlanetaryRepository
import com.adyen.android.assignment.features.main_screen.presentation.viewmodel.MainScreenResults
import javax.inject.Inject

class PlanetaryUseCase @Inject constructor(private val repository: PlanetaryRepository) {

    suspend fun execute(): MainScreenResults {
        return when (val response = repository.getPictures()) {
            is NetworkResponse.Success -> {
                response.data?.let {
                    MainScreenResults.AstronomyListLoaded(it)
                } ?: MainScreenResults.EmptyList
            }
            is NetworkResponse.Failure -> response.reason?.let {
                MainScreenResults.ErrorMessage(response.reason)
            } ?: MainScreenResults.ErrorMessage()
            is NetworkResponse.NoInternet -> MainScreenResults.NoInternetConnection
        }
    }
}