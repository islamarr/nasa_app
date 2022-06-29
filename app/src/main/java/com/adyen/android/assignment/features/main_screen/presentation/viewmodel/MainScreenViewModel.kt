package com.adyen.android.assignment.features.main_screen.presentation.viewmodel

import com.adyen.android.assignment.common.ui.BaseViewModel
import com.adyen.android.assignment.features.main_screen.domain.usecases.PlanetaryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val planetaryUseCase: PlanetaryUseCase) :
    BaseViewModel<MainScreenStates, MainScreenActions>(MainScreenStates.InitialState) {

    override fun handle(actions: MainScreenActions): Flow<MainScreenStates> = flow {
        when (actions) {
            is MainScreenActions.LoadAstronomyPicture -> emit(planetaryUseCase.execute())
            is MainScreenActions.SortByDate -> TODO()
            is MainScreenActions.SortByTitle -> TODO()
        }
    }

}