package com.adyen.android.assignment.data.remote.di

import android.content.Context
import com.adyen.android.assignment.data.remote.service.PlanetaryService
import com.adyen.android.assignment.utils.network.createMoshi
import com.adyen.android.assignment.utils.network.createRetrofitWithMoshi
import com.adyen.android.assignment.utils.base.app.NetworkConfig
import com.adyen.android.assignment.utils.network.createOkHttpClient
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton

private const val BASE_URL = "base_url"

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    @Singleton
    @Named(value = BASE_URL)
    fun provideBaseUrl(networkConfig: NetworkConfig): String {
        return networkConfig.baseUrl()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return createMoshi()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
    ): OkHttpClient {
        return createOkHttpClient(
            isCache = true,
            context = context
        )
    }


    @Provides
    @Singleton
    fun providePlanetaryService(
        @Named(value = BASE_URL) baseUrl: String,
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): PlanetaryService {
        return createRetrofitWithMoshi(
            okHttpClient = okHttpClient,
            moshi = moshi,
            baseUrl = baseUrl
        )
    }

}