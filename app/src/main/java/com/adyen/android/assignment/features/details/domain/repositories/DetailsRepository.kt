package com.adyen.android.assignment.features.details.domain.repositories

import com.adyen.android.assignment.features.main_screen.domain.entities.AstronomyPicture
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {
    suspend fun addToFavoriteList(astronomyPicture: AstronomyPicture)
    suspend fun removeFromFavoriteList(title: String?, date: String?)
    suspend fun getFavoriteList(): Flow<List<AstronomyPicture>>
    suspend fun getOneFavoriteItem(title: String?, date: String?): AstronomyPicture?
}