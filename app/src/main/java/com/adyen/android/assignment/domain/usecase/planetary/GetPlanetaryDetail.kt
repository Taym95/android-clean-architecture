package com.adyen.android.assignment.domain.usecase.planetary

import androidx.annotation.VisibleForTesting
import com.adyen.android.assignment.data.model.dto.planetary.PlanetaryDto
import com.adyen.android.assignment.data.model.dto.planetary.toPlanetaryDto
import com.adyen.android.assignment.data.repository.planetary.PlanetaryRepository
import com.adyen.android.assignment.utils.network.DataState
import com.adyen.android.assignment.utils.network.apiCall
import com.adyen.android.assignment.utils.usecase.DataStateUseCase
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetPlanetaryDetail @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val planetaryRepo: PlanetaryRepository,
) : DataStateUseCase<GetPlanetaryDetail.Params, PlanetaryDto>() {

    data class Params(
        val date: String,
    )

    override suspend fun FlowCollector<DataState<PlanetaryDto>>.execute(params: Params) {
        val getPlanetary =
            planetaryRepo.getPlanetary(date = params.date).toPlanetaryDto()

        val service = apiCall { getPlanetary }
        emit(service)
    }
}