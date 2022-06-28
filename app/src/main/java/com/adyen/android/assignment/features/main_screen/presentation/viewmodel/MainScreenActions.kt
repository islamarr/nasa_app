package com.adyen.android.assignment.features.main_screen.presentation.viewmodel

import com.adyen.android.assignment.common.Action

sealed class MainScreenActions : Action {
    data class LoadPictures(val count: Int) : MainScreenActions()
}
