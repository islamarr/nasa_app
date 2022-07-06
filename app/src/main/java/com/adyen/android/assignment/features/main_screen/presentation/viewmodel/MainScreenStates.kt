package com.adyen.android.assignment.features.main_screen.presentation.viewmodel

import com.adyen.android.assignment.common.ViewState
import com.adyen.android.assignment.features.main_screen.domain.entities.AstronomyPicture
import kotlinx.coroutines.flow.Flow


sealed class MainScreenStates : ViewState {
    object InitialState : MainScreenStates()
    object Loading : MainScreenStates()
    data class AstronomyListLoaded(val astronomyPictureList: List<AstronomyPicture>) :
        MainScreenStates()

    data class FavoriteListLoaded(val astronomyPictureList: List<AstronomyPicture>) :
        MainScreenStates()

    object EmptyList : MainScreenStates()
    object EmptyFavoriteList : MainScreenStates()
}
