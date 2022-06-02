package com.adyen.android.assignment.presentation.planetary.detail

import com.adyen.android.assignment.domain.mockdata.MockPlanetaryData
import org.junit.Assert
import org.junit.Test

class PlanetaryDetailDetailViewStateTest {

    private lateinit var event: PlanetaryDetailEvent

    private lateinit var state: PlanetaryDetailDetailViewState

    @Test
    fun verifyEventLoadDetail() {
        val date = "2022-05-31"
        event = PlanetaryDetailEvent.LoadPlanetaryDetail(date = date)

        val eventLoadDetail = event as PlanetaryDetailEvent.LoadPlanetaryDetail
        Assert.assertEquals(date, eventLoadDetail.date)
    }

    @Test
    fun verifyStatePlanetaryDetail() {
        val planetaryDto = MockPlanetaryData.getPlanetaryDto()
        state = PlanetaryDetailDetailViewState(planetaryDto)

        Assert.assertEquals(planetaryDto, state.planetaryDto)
    }
}