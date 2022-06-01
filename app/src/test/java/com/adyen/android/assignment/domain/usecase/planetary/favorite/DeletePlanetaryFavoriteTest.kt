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

class DeletePlanetaryFavoriteTest : MockkUnitTest() {

    @MockK(relaxed = true)
    lateinit var planetaryRepository: PlanetaryRepository

    @SpyK
    @InjectMockKs
    private lateinit var deletePlanetaryFavorite: DeletePlanetaryFavorite

    @Test
    fun verifyExecute() = runTest {
        val planetarydate = "2022-31-05"

        val params = DeletePlanetaryFavorite.Params(planetarydate)
        deletePlanetaryFavorite.invoke(params)

        coVerify { deletePlanetaryFavorite.invoke(any()) }
    }

    @Test
    fun collectExecute() = runTest {
        val planetarydate = "2022-31-05"
        val params = DeletePlanetaryFavorite.Params(planetarydate)

        deletePlanetaryFavorite.invoke(params).single()

        // Assert (Then)
        coVerify {
            planetaryRepository.deleteFavoritePlanetaryByDate(planetarydate)
        }
    }
}