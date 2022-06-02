package com.adyen.android.assignment.presentation.planetary.list

import androidx.annotation.StringRes
import com.adyen.android.assignment.BuildConfig
import com.adyen.android.assignment.R
import com.adyen.android.assignment.data.model.dto.planetary.PlanetaryDto
import com.adyen.android.assignment.domain.usecase.planetary.GetPlanetary
import com.adyen.android.assignment.domain.usecase.planetary.favorite.AddPlanetaryFavorite
import com.adyen.android.assignment.domain.usecase.planetary.favorite.DeletePlanetaryFavorite
import com.adyen.android.assignment.domain.usecase.planetary.favorite.GetPlanetaryFavorites
import com.adyen.android.assignment.utils.base.mvvm.BaseViewState
import com.adyen.android.assignment.utils.base.mvvm.MvvmIViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PlanetaryViewModel @Inject constructor(
    private val getPlanetary: GetPlanetary,
    private val getPlanetaryFavorites: GetPlanetaryFavorites,
    private val deletePlanetaryFavorite: DeletePlanetaryFavorite,
    private val addPlanetaryFavorite: AddPlanetaryFavorite
) : MvvmIViewModel<BaseViewState<PlanetaryViewState>, PlanetaryEvent>() {

    override fun onTriggerEvent(eventType: PlanetaryEvent) {
        when (eventType) {
            is PlanetaryEvent.LoadPlanetary -> onLoadPlanetary()
            is PlanetaryEvent.AddFavoritePlanetary -> onAddFavoritePlanetary(planetary = eventType.planetary)
            is PlanetaryEvent.DeleteFavoritePlanetary -> onDeleteFavoritePlanetary(eventType.date)
            is PlanetaryEvent.LoadFavoritesPlanetary -> onLoadFavoritesPlanetary()
        }
    }

    private fun onLoadPlanetary() = safeLaunch {
        setState(BaseViewState.Loading)
        val params = GetPlanetary.Params(count = 20)
        execute(getPlanetary(params)) { planetaryList ->
            setState(BaseViewState.Data(PlanetaryViewState(planetaryList = planetaryList)))
        }
    }

    private fun onAddFavoritePlanetary(planetary: PlanetaryDto) = safeLaunch {
        val params = AddPlanetaryFavorite.Params(planetary = planetary)
        call(addPlanetaryFavorite(params))
    }

    private fun onLoadFavoritesPlanetary() = safeLaunch {
        call(getPlanetaryFavorites(Unit)) {
            if (it.isEmpty()) {
                setState(BaseViewState.Empty)
            } else {
                setState(BaseViewState.Data(PlanetaryViewState(favorPlanetaryList = it)))
            }
        }
    }

    private fun onDeleteFavoritePlanetary(date: String) = safeLaunch {
        call(deletePlanetaryFavorite(DeletePlanetaryFavorite.Params(date))) {
            onTriggerEvent(PlanetaryEvent.LoadFavoritesPlanetary)
        }
    }
}

private enum class SortType(@StringRes val value: Int) {
    SORT_BY(R.string.planetary_list),
    PLANETARY_LIST_FAVORITE(R.string.planetary_favorite_list);
}