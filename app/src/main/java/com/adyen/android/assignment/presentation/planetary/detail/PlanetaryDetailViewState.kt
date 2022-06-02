package com.adyen.android.assignment.presentation.planetary.detail

import com.adyen.android.assignment.data.model.dto.planetary.PlanetaryDto


data class PlanetaryDetailDetailViewState(
    val planetaryDto: PlanetaryDto? = null
)

sealed class PlanetaryDetailEvent {
    data class LoadPlanetaryDetail(val date: String) : PlanetaryDetailEvent()
}