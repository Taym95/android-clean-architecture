package com.adyen.android.assignment.data.repository.di

import android.annotation.SuppressLint
import com.adyen.android.assignment.data.local.planetary.PlanetaryFavoriteDao
import com.adyen.android.assignment.data.remote.service.PlanetaryService
import com.adyen.android.assignment.data.repository.planetary.PlanetaryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@SuppressLint("VisibleForTests")
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providePlanetaryRepository(
        service: PlanetaryService,
        dao: PlanetaryFavoriteDao
    ) = PlanetaryRepository(service, dao)
}