package com.adyen.android.assignment.features.main_screen.domain.usecases

import com.adyen.android.assignment.features.details.domain.repositories.DetailsRepository
import com.adyen.android.assignment.features.main_screen.presentation.viewmodel.MainScreenResults
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class FavoriteListUseCase @Inject constructor(private val repository: DetailsRepository) {

    suspend fun execute(): Flow<MainScreenResults> = flow {

        val result = repository.getFavoriteList()
        result.collect {
            if (it.isEmpty()) emit(MainScreenResults.EmptyFavoriteList)
            else emit(MainScreenResults.FavoriteListLoaded(it))
        }


    }

}