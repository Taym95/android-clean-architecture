package com.adyen.android.assignment.domain.di

import android.annotation.SuppressLint
import com.adyen.android.assignment.data.repository.planetary.PlanetaryRepository
import com.adyen.android.assignment.domain.usecase.planetary.GetPlanetary
import com.adyen.android.assignment.domain.usecase.planetary.favorite.AddPlanetaryFavorite
import com.adyen.android.assignment.domain.usecase.planetary.favorite.DeletePlanetaryFavorite
import com.adyen.android.assignment.domain.usecase.planetary.favorite.GetPlanetaryFavorites
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@SuppressLint("VisibleForTests")
@Module
@InstallIn(SingletonComponent::class)
class PlanetaryDomainModule {

    @Singleton
    @Provides
    fun provideGetPlanetary(repository: PlanetaryRepository): GetPlanetary {
        return GetPlanetary(repository)
    }

    @Singleton
    @Provides
    fun provideAddPlanetaryFavorite(repository: PlanetaryRepository): AddPlanetaryFavorite {
        return AddPlanetaryFavorite(repository)
    }

    @Singleton
    @Provides
    fun provideDeletePlanetaryFavorite(repository: PlanetaryRepository): DeletePlanetaryFavorite {
        return DeletePlanetaryFavorite(repository)
    }

    @Singleton
    @Provides
    fun provideGetPlanetaryFavorites(repository: PlanetaryRepository): GetPlanetaryFavorites {
        return GetPlanetaryFavorites(repository)
    }
}