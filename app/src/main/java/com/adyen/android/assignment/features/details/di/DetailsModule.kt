package com.adyen.android.assignment.features.details.di

import com.adyen.android.assignment.features.details.data.repositories.DetailsRepositoryImpl
import com.adyen.android.assignment.features.details.domain.repositories.DetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class DetailsModule {

    @Binds
    abstract fun bindAlbumDetailsRepository(repository: DetailsRepositoryImpl): DetailsRepository

}