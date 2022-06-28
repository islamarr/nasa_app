package com.adyen.android.assignment.features.main_screen.di

import com.adyen.android.assignment.features.main_screen.data.remote.api.PlanetaryService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class PlanetaryAPIModule {

    @Provides
    fun provideAPIService(retrofit: Retrofit): PlanetaryService {
        return retrofit.create(PlanetaryService::class.java)
    }

}