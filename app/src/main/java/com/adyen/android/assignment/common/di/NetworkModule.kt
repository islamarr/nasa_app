package com.adyen.android.assignment.common.di

import com.adyen.android.assignment.BuildConfig
import com.adyen.android.assignment.common.TIME_OUT_IN_SECONDS
import com.adyen.android.assignment.common.data.ConnectivityInterceptorImpl
import com.adyen.android.assignment.common.data.URLInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        if (BuildConfig.DEBUG) httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        urlInterceptor: URLInterceptor,
        connectivityInterceptor: ConnectivityInterceptorImpl
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(urlInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(connectivityInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideMoshiBuilder(): Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun provideMoshiConverterFactory(
        moshi: Moshi
    ): MoshiConverterFactory = MoshiConverterFactory.create(moshi)


    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.NASA_BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

}