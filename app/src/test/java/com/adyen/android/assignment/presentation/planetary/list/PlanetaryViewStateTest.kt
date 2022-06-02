package com.adyen.android.assignment.presentation.planetary.list

import androidx.paging.PagingData
import com.adyen.android.assignment.data.model.dto.planetary.PlanetaryDto
import com.adyen.android.assignment.domain.mockdata.MockPlanetaryData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Test

class PlanetaryViewStateText {
    private lateinit var planetaryEvent: PlanetaryEvent

    private lateinit var planetaryViewState: PlanetaryViewState

    @Test
    fun verifyEventLoadPlanetary() {
        planetaryEvent = PlanetaryEvent.LoadPlanetary

        val eventLoadDetail = planetaryEvent as PlanetaryEvent.LoadPlanetary
        Assert.assertEquals(planetaryEvent, eventLoadDetail)
    }

    @Test
    fun verifyEventAddOrRemoveFavorite() {
        val planetaryDto = MockPlanetaryData.getPlanetaryDto()
        planetaryEvent = PlanetaryEvent.DeleteFavoritePlanetary(planetaryDto.date!!)

        val eventDeleteFavorite = planetaryEvent as PlanetaryEvent.DeleteFavoritePlanetary
        Assert.assertEquals(planetaryEvent, eventDeleteFavorite)
    }

    @Test
    fun verifyEventLoadFavorites() {
        planetaryEvent = PlanetaryEvent.LoadFavoritesPlanetary

        val eventLoadFavoritesPlanetary = planetaryEvent as PlanetaryEvent.LoadFavoritesPlanetary
        Assert.assertEquals(planetaryEvent, eventLoadFavoritesPlanetary)
    }

    @Test
    fun verifyStatePlanetary() {
        val planetaryList: List<PlanetaryDto> = listOf(MockPlanetaryData.getPlanetaryDto())
        planetaryViewState = PlanetaryViewState(planetaryList, emptyList())

        Assert.assertEquals(planetaryList, planetaryViewState.planetaryList)
        Assert.assertEquals(0, planetaryViewState.favorPlanetaryList.size)
    }
}