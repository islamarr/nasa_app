package com.adyen.android.assignment.features.details.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adyen.android.assignment.features.main_screen.domain.entities.AstronomyPicture

@Database(
    entities = [AstronomyPicture::class],
    version = 1
)
abstract class FavoriteDatabase : RoomDatabase() {

    abstract fun getFavoriteDao(): FavoriteDao

    companion object {

        @Volatile
        private var instance: FavoriteDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                FavoriteDatabase::class.java,
                "Favorite.db"
            ).build()
    }
}