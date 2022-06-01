package com.adyen.android.assignment.domain.usecase.planetary.favorite

import androidx.annotation.VisibleForTesting
import com.adyen.android.assignment.data.model.dto.planetary.PlanetaryDto
import com.adyen.android.assignment.data.model.dto.planetary.toPlanetaryList
import com.adyen.android.assignment.data.repository.planetary.PlanetaryRepository
import com.adyen.android.assignment.utils.usecase.LocalUseCase
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetPlanetaryFavorites @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val repository: PlanetaryRepository
) : LocalUseCase<Unit, List<PlanetaryDto>>() {

    override suspend fun FlowCollector<List<PlanetaryDto>>.execute(params: Unit) {
        val favors = repository.getFavoritePlanetaryList()
        emit(favors.toPlanetaryList())
    }
}