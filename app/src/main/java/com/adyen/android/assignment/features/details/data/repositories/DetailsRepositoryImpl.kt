package com.adyen.android.assignment.features.details.data.repositories

import com.adyen.android.assignment.common.AppCoroutineDispatchers
import com.adyen.android.assignment.features.details.data.db.FavoriteDao
import com.adyen.android.assignment.features.details.domain.repositories.DetailsRepository
import com.adyen.android.assignment.features.main_screen.domain.entities.AstronomyPicture
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(
    private val favoriteDao: FavoriteDao,
    private val dispatchers: AppCoroutineDispatchers
) : DetailsRepository {

    override suspend fun addToFavoriteList(astronomyPicture: AstronomyPicture) {
        favoriteDao.addToFavoriteList(astronomyPicture)
    }

    override suspend fun removeFromFavoriteList(title: String?, date: String?) {
        favoriteDao.removeFromFavoriteList(title ?: "", date ?: "")
    }

    override suspend fun getFavoriteList(): Flow<List<AstronomyPicture>> =
        withContext(dispatchers.io) {
            favoriteDao.getFavoriteList()
        }

    override suspend fun getOneFavoriteItem(title: String?, date: String?): AstronomyPicture? =
        withContext(dispatchers.io) {
            favoriteDao.getOneFavoriteAlbum(title ?: "", date ?: "")
        }

}