package com.adyen.android.assignment.features.main_screen.presentation.viewmodel

import com.adyen.android.assignment.common.ui.BaseViewModel
import com.adyen.android.assignment.features.main_screen.domain.usecases.PlanetaryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val planetaryUseCase: PlanetaryUseCase) :
    BaseViewModel<MainScreenStates, MainScreenActions, MainScreenEvents, MainScreenResults>(
        MainScreenStates.InitialState
    ) {

    override fun handle(actions: MainScreenActions): Flow<MainScreenResults> = flow {
        when (actions) {
            is MainScreenActions.LoadAstronomyPicture -> {
                emit(MainScreenResults.Loading)
                emit(planetaryUseCase.execute())
            }
            is MainScreenActions.SortByDate -> TODO()
            is MainScreenActions.SortByTitle -> TODO()
        }
    }

    override fun reduce(result: MainScreenResults): MainScreenStates {
        return when (result) {
            is MainScreenResults.ErrorMessage -> {
                onViewEvent(MainScreenEvents.NavigateToErrorScreen)
                currentState
            }
            is MainScreenResults.NoInternetConnection -> {
                onViewEvent(MainScreenEvents.NavigateToNoInternetScreen)
                currentState
            }
            is MainScreenResults.AstronomyListLoaded -> MainScreenStates.AstronomyListLoaded(result.astronomyPictureList)
            is MainScreenResults.EmptyList -> MainScreenStates.EmptyList
            is MainScreenResults.Loading -> MainScreenStates.Loading

        }

    }

}