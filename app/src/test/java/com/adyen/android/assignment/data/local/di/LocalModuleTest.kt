package com.adyen.android.assignment.data.local.di

import android.content.Context
import com.adyen.android.assignment.BuildConfig
import com.adyen.android.assignment.data.local.db.PlanetaryDatabase
import com.adyen.android.assignment.data.local.planetary.PlanetaryFavoriteDao
import com.adyen.android.assignment.testutils.MockkUnitTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

class LocalModuleTest : MockkUnitTest() {

    private lateinit var localModule: LocalModule

    override fun onCreate() {
        super.onCreate()
        localModule = LocalModule()
    }

    @Test
    fun verifyProvideDatabaseName() {
        val databaseName = localModule.provideDatabaseName()
        assertEquals(databaseName, BuildConfig.DB_NAME)
    }

    @Test
    fun verifyProvideDatabase() {
        val context: Context = mockk()
        val databaseName = localModule.provideDatabaseName()
        val database = localModule.provideDatabase(databaseName, context)

        Assert.assertNotNull(database.planetaryFavoriteDao())
    }


    @Test
    fun verifyProvidePlanetaryFavoriteDao() {
        val database: PlanetaryDatabase = mockk()
        val dao: PlanetaryFavoriteDao = mockk()

        every { database.planetaryFavoriteDao() } returns dao

        assertEquals(
            dao,
            localModule.providePlanetaryFavoriteDao(database)
        )
        verify { database.planetaryFavoriteDao() }
    }
}