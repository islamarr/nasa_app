package com.adyen.android.assignment.features.main_screen.domain.usecases

import com.adyen.android.assignment.common.DATE_SORT
import com.adyen.android.assignment.common.TITLE_SORT
import com.adyen.android.assignment.features.main_screen.domain.entities.AstronomyPicture
import com.adyen.android.assignment.features.main_screen.presentation.viewmodel.MainScreenResults
import javax.inject.Inject

class SortListUseCase @Inject constructor() {

    fun execute(
        sortType: Int,
        list: List<AstronomyPicture>
    ): MainScreenResults {
        return when (sortType) {
            TITLE_SORT -> MainScreenResults.FilteredList(list.sortedBy { it.title })
            DATE_SORT -> MainScreenResults.FilteredList(list.sortedByDescending { it.date })
            else -> MainScreenResults.AstronomyListLoaded(list)
        }
    }
}