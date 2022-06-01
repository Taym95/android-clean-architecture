package com.adyen.android.assignment.domain.usecase.planetary

import androidx.annotation.VisibleForTesting
import com.adyen.android.assignment.data.model.dto.planetary.PlanetaryDto
import com.adyen.android.assignment.data.model.dto.planetary.toPlanetaryDtoList
import com.adyen.android.assignment.data.repository.planetary.PlanetaryRepository
import com.adyen.android.assignment.utils.network.DataState
import com.adyen.android.assignment.utils.network.apiCall
import com.adyen.android.assignment.utils.usecase.DataStateUseCase
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetPlanetary @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val planetaryRepo: PlanetaryRepository,
) : DataStateUseCase<GetPlanetary.Params, List<PlanetaryDto>>() {

    data class Params(
        val count: Int,
        val apiKey: String,
    )


    override suspend fun FlowCollector<DataState<List<PlanetaryDto>>>.execute(params: Params) {
        val getPlanetary =
            planetaryRepo.getPlanetaryList(count = params.count, apiKey = params.apiKey)
                .toPlanetaryDtoList()

        val service = apiCall { getPlanetary }

        emit(service)
    }
}