package com.adyen.android.assignment.domain.usecase.planetary.favorite

import androidx.annotation.VisibleForTesting
import com.adyen.android.assignment.data.model.dto.planetary.PlanetaryDto
import com.adyen.android.assignment.data.model.dto.planetary.toPlanetaryEntity
import com.adyen.android.assignment.data.repository.planetary.PlanetaryRepository
import com.adyen.android.assignment.utils.usecase.LocalUseCase
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject


class AddPlanetaryFavorite @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val repository: PlanetaryRepository
) : LocalUseCase<AddPlanetaryFavorite.Params, Unit>() {

    data class Params(
        val planetary: PlanetaryDto
    )

    override suspend fun FlowCollector<Unit>.execute(params: Params) {
        val dto = params.planetary
        repository.saveFavoritePlanetary(dto.toPlanetaryEntity())
        emit(Unit)
    }
}