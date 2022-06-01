package com.adyen.android.assignment.data.local.di

import android.content.Context
import androidx.room.Room
import com.adyen.android.assignment.BuildConfig
import com.adyen.android.assignment.data.local.db.PlanetaryDatabase
import com.adyen.android.assignment.data.local.planetary.PlanetaryFavoriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

private const val DB_NAME = "db_name"

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {
    @Provides
    @Singleton
    @Named(value = DB_NAME)
    fun provideDatabaseName(): String {
        return BuildConfig.DB_NAME
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @Named(value = DB_NAME) dbname: String,
        @ApplicationContext context: Context
    ): PlanetaryDatabase {
        return Room.databaseBuilder(context, PlanetaryDatabase::class.java, dbname)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePlanetaryFavoriteDao(appDatabase: PlanetaryDatabase): PlanetaryFavoriteDao {
        return appDatabase.planetaryFavoriteDao()
    }

}