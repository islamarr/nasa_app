package com.adyen.android.assignment.features.details.di

import android.content.Context
import com.adyen.android.assignment.features.details.data.db.FavoriteDao
import com.adyen.android.assignment.features.details.data.db.FavoriteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FavoriteDataBaseModule {

    @Provides
    @Singleton
    fun provideFavoriteDao(db: FavoriteDatabase): FavoriteDao = db.getFavoriteDao()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): FavoriteDatabase =
        FavoriteDatabase.invoke(appContext)
}