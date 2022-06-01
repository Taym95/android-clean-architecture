package com.adyen.android.assignment.domain.usecase.planetary.favorite

import com.adyen.android.assignment.data.model.dto.planetary.PlanetaryDto
import com.adyen.android.assignment.data.model.dto.planetary.toPlanetaryEntity
import com.adyen.android.assignment.data.repository.planetary.PlanetaryRepository
import com.adyen.android.assignment.domain.mockdata.MockPlanetaryData
import com.adyen.android.assignment.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.mockk
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AddPlanetaryFavoriteTest : MockkUnitTest() {

    @MockK(relaxed = true)
    lateinit var planetaryRepository: PlanetaryRepository

    @SpyK
    @InjectMockKs
    private lateinit var addPlanetaryFavorite: AddPlanetaryFavorite

    @Test
    fun verifyExecute() = runTest {
        val dto = mockk<PlanetaryDto>()

        val params = AddPlanetaryFavorite.Params(dto)
        addPlanetaryFavorite.invoke(params)

        coVerify { addPlanetaryFavorite.invoke(any()) }
    }

    @Test
    fun collectExecute() = runTest {
        val dto = MockPlanetaryData.getPlanetaryDto()
        val params = AddPlanetaryFavorite.Params(dto)

        addPlanetaryFavorite.invoke(params).single()

        coVerify {
            planetaryRepository.saveFavoritePlanetary(dto.toPlanetaryEntity())
        }
    }
}