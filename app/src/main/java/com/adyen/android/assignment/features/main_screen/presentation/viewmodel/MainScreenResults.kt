package com.adyen.android.assignment.features.main_screen.presentation.viewmodel

import com.adyen.android.assignment.common.Results
import com.adyen.android.assignment.common.ViewState
import com.adyen.android.assignment.features.main_screen.domain.entities.AstronomyPicture
import kotlinx.coroutines.flow.Flow


sealed class MainScreenResults : Results {
    object Loading : MainScreenResults()
    data class AstronomyListLoaded(val astronomyPictureList: List<AstronomyPicture>) :
        MainScreenResults()
    object EmptyList : MainScreenResults()
    data class ErrorMessage(val reason: String? = null) : MainScreenResults()
    object NoInternetConnection : MainScreenResults()
    data class FavoriteListLoaded(val astronomyPictureList: List<AstronomyPicture>) :
        MainScreenResults()
    object EmptyFavoriteList : MainScreenResults()
}
