package com.adyen.android.assignment.features.main_screen.domain.usecases

import com.adyen.android.assignment.common.data.NetworkResponse
import com.adyen.android.assignment.features.main_screen.domain.repositories.PlanetaryRepository
import com.adyen.android.assignment.features.main_screen.presentation.viewmodel.MainScreenStates
import javax.inject.Inject

class PlanetaryUseCase @Inject constructor(private val repository: PlanetaryRepository) {

    suspend fun execute(): MainScreenStates {
        return when (val response = repository.getPictures()) {
            is NetworkResponse.Success -> {
                response.data?.let {
                    MainScreenStates.AstronomyListLoaded(it)
                } ?: MainScreenStates.EmptyList
            }
            is NetworkResponse.Failure -> response.reason?.let {
                MainScreenStates.ShowErrorMessage(response.reason)
            } ?: MainScreenStates.ShowErrorMessage()
        }
    }
}