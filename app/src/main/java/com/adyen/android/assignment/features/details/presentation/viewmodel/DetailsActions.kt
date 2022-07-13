package com.adyen.android.assignment.features.details.presentation.viewmodel

import com.adyen.android.assignment.common.Action
import com.adyen.android.assignment.features.main_screen.domain.entities.AstronomyPicture

sealed class DetailsActions : Action {
    data class CheckAstronomyPictureFavorite(val title: String, val date: String) : DetailsActions()
    data class SetFavoriteAction(val isAdd: Boolean, val astronomyPicture: AstronomyPicture) :
        DetailsActions()
}
