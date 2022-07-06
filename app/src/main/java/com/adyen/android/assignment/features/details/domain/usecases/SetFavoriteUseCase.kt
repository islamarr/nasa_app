package com.adyen.android.assignment.features.details.domain.usecases

import com.adyen.android.assignment.features.details.domain.repositories.DetailsRepository
import com.adyen.android.assignment.features.main_screen.domain.entities.AstronomyPicture
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class SetFavoriteUseCase @Inject constructor(private val repository: DetailsRepository) {

    suspend fun execute(isAdd: Boolean, astronomyPicture: AstronomyPicture) {
        when {
            isAdd -> repository.addToFavoriteList(astronomyPicture)
            else -> repository.removeFromFavoriteList(astronomyPicture.title, astronomyPicture.date)
        }
    }

}