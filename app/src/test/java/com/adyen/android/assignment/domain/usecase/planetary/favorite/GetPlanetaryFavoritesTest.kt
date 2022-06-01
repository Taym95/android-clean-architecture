package com.adyen.android.assignment.domain.usecase.planetary.favorite

import com.adyen.android.assignment.data.repository.planetary.PlanetaryRepository
import com.adyen.android.assignment.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetPlanetaryFavoritesTest : MockkUnitTest() {

    @MockK(relaxed = true)
    lateinit var planetaryRepository: PlanetaryRepository

    @SpyK
    @InjectMockKs
    private lateinit var getFavoritesPlanetary: GetPlanetaryFavorites

    @Test
    fun verifyExecute() = runTest {
        getFavoritesPlanetary.invoke(Unit)

        coVerify { getFavoritesPlanetary.invoke(Unit) }
    }

    @Test
    fun collectExecute() = runTest {
        getFavoritesPlanetary.invoke(Unit).single()

        coVerify { planetaryRepository.getFavoritePlanetaryList() }
    }
}