package com.adyen.android.assignment.features.main_screen.presentation.viewmodel

import com.adyen.android.assignment.common.ViewState


sealed class MainScreenStates : ViewState {
    object InitialState : MainScreenStates()

}
