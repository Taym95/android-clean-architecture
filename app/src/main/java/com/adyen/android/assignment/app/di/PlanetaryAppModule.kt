package com.adyen.android.assignment.app.di

import com.adyen.android.assignment.app.PlanetaryApp
import com.adyen.android.assignment.app.PlanetaryNetworkConfig
import com.adyen.android.assignment.utils.base.app.NetworkConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PlanetaryAppModule {
    @Provides
    @Singleton
    fun provideApplication(): PlanetaryApp {
        return PlanetaryApp()
    }

    @Provides
    @Singleton
    fun provideNetworkConfig(): NetworkConfig {
        return PlanetaryNetworkConfig()
    }

}