package com.adyen.android.assignment.domain.di

import com.adyen.android.assignment.data.repository.planetary.PlanetaryRepository
import com.adyen.android.assignment.testutils.MockkUnitTest
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test


class PlanetaryDomainModuleTest : MockkUnitTest() {

    private lateinit var module: PlanetaryDomainModule

    override fun onCreate() {
        super.onCreate()
        module = PlanetaryDomainModule()
    }

    @Test
    fun verifyProvideGetPlanetary() {
        val repository = mockk<PlanetaryRepository>()
        val getPlanetary = module.provideGetPlanetary(repository)

        Assert.assertEquals(repository, getPlanetary.planetaryRepo)
    }

    @Test
    fun verifyProvideGetPlanetaryFavorites() {
        val repository = mockk<PlanetaryRepository>()
        val getFavoriteList = module.provideDeletePlanetaryFavorite(repository)

        Assert.assertEquals(repository, getFavoriteList.repository)
    }

    @Test
    fun verifyProvideAddPlanetaryFavorite() {
        val repository = mockk<PlanetaryRepository>()
        val addFavorite = module.provideAddPlanetaryFavorite(repository)

        Assert.assertEquals(repository, addFavorite.repository)
    }

    @Test
    fun verifyProvideDeletePlanetaryFavorite() {
        val repository = mockk<PlanetaryRepository>()
        val deleteFavorite = module.provideDeletePlanetaryFavorite(repository)

        Assert.assertEquals(repository, deleteFavorite.repository)
    }
}