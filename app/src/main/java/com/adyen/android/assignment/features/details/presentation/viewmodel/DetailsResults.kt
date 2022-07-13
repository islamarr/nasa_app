package com.adyen.android.assignment.features.details.presentation.viewmodel

import com.adyen.android.assignment.common.Results


sealed class DetailsResults : Results {
    data class SavedState(val isSaved: Boolean) : DetailsResults()
}
