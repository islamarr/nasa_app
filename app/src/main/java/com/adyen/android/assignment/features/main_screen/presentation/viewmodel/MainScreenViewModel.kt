package com.adyen.android.assignment.features.main_screen.presentation.viewmodel

import com.adyen.android.assignment.common.UNSORTED
import com.adyen.android.assignment.common.ui.BaseViewModel
import com.adyen.android.assignment.features.main_screen.domain.entities.AstronomyPicture
import com.adyen.android.assignment.features.main_screen.domain.usecases.FavoriteListUseCase
import com.adyen.android.assignment.features.main_screen.domain.usecases.PlanetaryUseCase
import com.adyen.android.assignment.features.main_screen.domain.usecases.SortListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val planetaryUseCase: PlanetaryUseCase,
    private val sortListUseCase: SortListUseCase,
    private val favoriteListUseCase: FavoriteListUseCase
) :
    BaseViewModel<MainScreenStates, MainScreenActions, MainScreenEvents, MainScreenResults>(
        MainScreenStates.InitialState
    ) {

    var orderTypeSelected = UNSORTED
    private var currentList = listOf<AstronomyPicture>()

    override fun handle(actions: MainScreenActions): Flow<MainScreenResults> = flow {
        when (actions) {
            is MainScreenActions.LoadAstronomyPicture -> {
                emit(MainScreenResults.Loading)
                emit(planetaryUseCase.execute())
            }
            is MainScreenActions.SortList -> emit(
                sortListUseCase.execute(
                    actions.sortType,
                    currentList
                )
            )
            is MainScreenActions.LoadFavoriteList -> emitAll(favoriteListUseCase.execute())
        }
    }

    override fun reduce(result: MainScreenResults): MainScreenStates {
        return when (result) {
            is MainScreenResults.ErrorMessage -> {
                onViewEvent(MainScreenEvents.NavigateToErrorScreen)
                MainScreenStates.InitialState
            }
            is MainScreenResults.NoInternetConnection -> {
                onViewEvent(MainScreenEvents.NavigateToNoInternetScreen)
                MainScreenStates.InitialState
            }
            is MainScreenResults.AstronomyListLoaded -> {
                currentList = result.astronomyPictureList
                MainScreenStates.AstronomyListLoaded(currentList)
            }
            is MainScreenResults.FilteredList -> {
                MainScreenStates.FilteredList(result.astronomyPictureList)
            }
            is MainScreenResults.EmptyList -> MainScreenStates.EmptyList
            is MainScreenResults.Loading -> MainScreenStates.Loading
            is MainScreenResults.EmptyFavoriteList -> MainScreenStates.EmptyFavoriteList
            is MainScreenResults.FavoriteListLoaded -> MainScreenStates.FavoriteListLoaded(result.astronomyPictureList)

        }

    }

}