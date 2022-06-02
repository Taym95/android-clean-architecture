package com.adyen.android.assignment.presentation.planetary.list

import com.adyen.android.assignment.data.model.dto.planetary.PlanetaryDto

data class PlanetaryViewState(
    val planetaryList: List<PlanetaryDto> = emptyList(),
    val favorPlanetaryList: List<PlanetaryDto> = emptyList()
)

sealed class PlanetaryEvent {
    object LoadPlanetary : PlanetaryEvent()
    object LoadFavoritesPlanetary : PlanetaryEvent()
    data class DeleteFavoritePlanetary(val date: String) : PlanetaryEvent()
    data class AddFavoritePlanetary(val planetary: PlanetaryDto) : PlanetaryEvent()
    data class OrderPlanetary(val reorderType: ReorderType, val planetary: List<PlanetaryDto>) :
        PlanetaryEvent()
}