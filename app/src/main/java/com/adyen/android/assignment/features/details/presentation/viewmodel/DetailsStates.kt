package com.adyen.android.assignment.features.details.presentation.viewmodel

import com.adyen.android.assignment.common.ViewState


sealed class DetailsStates : ViewState {
    object InitialState : DetailsStates()
    data class SavedState(val isSaved: Boolean) : DetailsStates()
}
