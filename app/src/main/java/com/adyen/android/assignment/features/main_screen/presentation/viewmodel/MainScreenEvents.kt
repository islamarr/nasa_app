package com.adyen.android.assignment.features.main_screen.presentation.viewmodel

import com.adyen.android.assignment.common.ViewEvents

sealed class MainScreenEvents : ViewEvents {
    object NavigateToErrorScreen : MainScreenEvents()
    object NavigateToNoInternetScreen : MainScreenEvents()
}
