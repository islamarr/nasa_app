package com.adyen.android.assignment.features.main_screen.di

import com.adyen.android.assignment.features.main_screen.data.repositories.PlanetaryRepositoryImpl
import com.adyen.android.assignment.features.main_screen.domain.repositories.PlanetaryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class PlanetaryModule {

    @Binds
    abstract fun bindTopAlbumsRepository(repository: PlanetaryRepositoryImpl): PlanetaryRepository

}