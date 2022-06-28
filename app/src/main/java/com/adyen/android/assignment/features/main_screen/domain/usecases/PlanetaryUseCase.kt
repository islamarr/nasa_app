package com.adyen.android.assignment.features.main_screen.domain.usecases

import android.util.Log
import com.adyen.android.assignment.common.data.NetworkResponse
import com.adyen.android.assignment.features.main_screen.domain.repositories.PlanetaryRepository
import com.adyen.android.assignment.features.main_screen.presentation.viewmodel.MainScreenStates
import javax.inject.Inject

class PlanetaryUseCase @Inject constructor(private val repository: PlanetaryRepository) {

    suspend fun execute(): MainScreenStates {
        return when (val response = repository.getPictures()) {
            is NetworkResponse.Success -> {
                response.data?.let {
                    Log.d("zxcc", "execute: ${it[0].date}")
                    MainScreenStates.InitialState
                } ?: MainScreenStates.InitialState
            }
            is NetworkResponse.Failure -> MainScreenStates.InitialState
        }
    }
}