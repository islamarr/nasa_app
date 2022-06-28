package com.adyen.android.assignment.common.di

import com.adyen.android.assignment.common.AppCoroutineDispatchers
import com.adyen.android.assignment.common.AppCoroutineDispatchersImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DispatcherModule {

    @Binds
    abstract fun bindDispatchers(dispatchers: AppCoroutineDispatchersImpl): AppCoroutineDispatchers
}