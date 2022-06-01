package com.adyen.android.assignment.domain.usecase.planetary.favorite

import androidx.annotation.VisibleForTesting
import com.adyen.android.assignment.data.repository.planetary.PlanetaryRepository
import com.adyen.android.assignment.utils.usecase.LocalUseCase
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject


class DeletePlanetaryFavorite @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val repository: PlanetaryRepository
) : LocalUseCase<DeletePlanetaryFavorite.Params, Unit>() {

    data class Params(
        val planetaryDate: String
    )

    override suspend fun FlowCollector<Unit>.execute(params: Params) {
        repository.deleteFavoritePlanetaryByDate(params.planetaryDate)
        emit(Unit)
    }
}