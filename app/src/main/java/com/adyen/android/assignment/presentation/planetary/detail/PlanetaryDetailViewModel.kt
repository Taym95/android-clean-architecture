package com.adyen.android.assignment.presentation.planetary.detail

import com.adyen.android.assignment.domain.usecase.planetary.GetPlanetaryDetail
import com.adyen.android.assignment.utils.base.mvvm.BaseViewState
import com.adyen.android.assignment.utils.base.mvvm.MvvmIViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlanetaryDetailViewModel @Inject constructor(
    private val getPlanetaryDetail: GetPlanetaryDetail,
) : MvvmIViewModel<BaseViewState<PlanetaryDetailDetailViewState>, PlanetaryDetailEvent>() {

    override fun onTriggerEvent(eventType: PlanetaryDetailEvent) {
        when (eventType) {
            is PlanetaryDetailEvent.LoadPlanetaryDetail -> onLoadDetail(eventType.date)
        }
    }

    private fun onLoadDetail(date: String) = safeLaunch {
        val params = GetPlanetaryDetail.Params(date = date)
        execute(getPlanetaryDetail(params)) { planetaryDto ->
            setState(BaseViewState.Data(PlanetaryDetailDetailViewState(planetaryDto = planetaryDto)))
        }
    }
}