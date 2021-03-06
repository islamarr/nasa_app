package com.adyen.android.assignment.features.details.domain.usecases

import com.adyen.android.assignment.features.details.domain.repositories.DetailsRepository
import com.adyen.android.assignment.features.details.presentation.viewmodel.DetailsResults
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetFavoriteUseCase @Inject constructor(private val repository: DetailsRepository) {

    suspend fun execute(title: String?, date: String?): DetailsResults {
        val result = repository.getOneFavoriteItem(title, date)
        return result?.let {
            DetailsResults.SavedState(true)
        } ?: DetailsResults.SavedState(false)
    }

}