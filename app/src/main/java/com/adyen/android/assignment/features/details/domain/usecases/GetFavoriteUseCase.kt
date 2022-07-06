package com.adyen.android.assignment.features.details.domain.usecases

import com.adyen.android.assignment.features.details.domain.repositories.DetailsRepository
import com.adyen.android.assignment.features.details.presentation.viewmodel.DetailsResults
import com.adyen.android.assignment.features.details.presentation.viewmodel.DetailsStates
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class GetFavoriteUseCase @Inject constructor(private val repository: DetailsRepository) {

    suspend fun execute(title: String?, date: String?): DetailsResults {
        return withContext(Dispatchers.IO) {
            val result = repository.getOneFavoriteItem(title, date)
             result?.let {
                DetailsResults.SavedState(true)
            } ?: DetailsResults.SavedState(false)
        }
    }

}