package com.adyen.android.assignment.features.main_screen.domain.usecases

import com.adyen.android.assignment.features.main_screen.domain.entities.AstronomyPicture
import com.adyen.android.assignment.features.main_screen.presentation.viewmodel.MainScreenResults
import javax.inject.Inject

class SortListUseCase @Inject constructor() {

    fun execute(
        sortType: Int,
        list: List<AstronomyPicture>
    ): MainScreenResults {
        return when (sortType) {
            0 -> MainScreenResults.AstronomyListLoaded(list.sortedBy { it.title })
            1 -> MainScreenResults.AstronomyListLoaded(list.sortedByDescending { it.date })
            else -> MainScreenResults.AstronomyListLoaded(list)
        }
    }
}