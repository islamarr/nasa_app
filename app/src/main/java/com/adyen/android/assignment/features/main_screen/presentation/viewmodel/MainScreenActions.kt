package com.adyen.android.assignment.features.main_screen.presentation.viewmodel

import com.adyen.android.assignment.common.Action

sealed class MainScreenActions : Action {
    object LoadAstronomyPicture : MainScreenActions()
    object SortByTitle : MainScreenActions()
    object SortByDate : MainScreenActions()
}
