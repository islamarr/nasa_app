package com.adyen.android.assignment.features.details.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adyen.android.assignment.features.main_screen.domain.entities.AstronomyPicture
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavoriteList(astronomyPicture: AstronomyPicture): Long

    @Query("DELETE FROM favorite WHERE title = :title AND date = :date")
    suspend fun removeFromFavoriteList(title: String, date: String)

    @Query("SELECT * FROM favorite")
    fun getFavoriteList(): Flow<List<AstronomyPicture>>

    @Query("SELECT * FROM favorite WHERE title = :title AND date = :date")
    fun getOneFavoriteAlbum(title: String, date: String): AstronomyPicture?

}