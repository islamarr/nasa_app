package com.adyen.android.assignment.features.main_screen.presentation.viewmodel

import com.adyen.android.assignment.common.Results
import com.adyen.android.assignment.features.main_screen.domain.entities.AstronomyPicture


sealed class MainScreenResults : Results {
    object Loading : MainScreenResults()
    data class AstronomyListLoaded(val astronomyPictureList: List<AstronomyPicture>) :
        MainScreenResults()

    data class FilteredList(val astronomyPictureList: List<AstronomyPicture>) :
        MainScreenResults()

    object EmptyList : MainScreenResults()
    data class ErrorMessage(val reason: String? = null) : MainScreenResults()
    object NoInternetConnection : MainScreenResults()
    data class FavoriteListLoaded(val astronomyPictureList: List<AstronomyPicture>) :
        MainScreenResults()

    object EmptyFavoriteList : MainScreenResults()
}
