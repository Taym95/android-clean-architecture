package com.adyen.android.assignment.data.repository.di

import com.adyen.android.assignment.data.local.planetary.PlanetaryFavoriteDao
import com.adyen.android.assignment.data.remote.service.PlanetaryService
import com.adyen.android.assignment.testutils.MockkUnitTest
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class RepositoryModuleTest : MockkUnitTest() {

    private lateinit var repositoryModule: RepositoryModule

    override fun onCreate() {
        super.onCreate()
        repositoryModule = RepositoryModule()
    }


    @Test
    fun verifyProvidePlanetaryRepository() {
        val service = mockk<PlanetaryService>()
        val dao = mockk<PlanetaryFavoriteDao>()
        val repository = repositoryModule.providePlanetaryRepository(service, dao)

        Assert.assertEquals(service, repository.service)
        Assert.assertEquals(dao, repository.dao)
    }

}