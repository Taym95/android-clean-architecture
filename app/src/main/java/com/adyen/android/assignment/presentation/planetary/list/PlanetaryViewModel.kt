package com.adyen.android.assignment.presentation.planetary.list

import android.os.Build
import androidx.annotation.RequiresApi
import com.adyen.android.assignment.data.model.dto.planetary.PlanetaryDto
import com.adyen.android.assignment.domain.usecase.planetary.GetPlanetary
import com.adyen.android.assignment.domain.usecase.planetary.favorite.AddPlanetaryFavorite
import com.adyen.android.assignment.domain.usecase.planetary.favorite.DeletePlanetaryFavorite
import com.adyen.android.assignment.domain.usecase.planetary.favorite.GetPlanetaryFavorites
import com.adyen.android.assignment.utils.base.mvvm.BaseViewState
import com.adyen.android.assignment.utils.base.mvvm.MvvmIViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
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
            is PlanetaryEvent.OrderPlanetary -> onOrderPlanetary(
                eventType.reorderType,
                eventType.planetary
            )
        }
    }

    private fun onLoadPlanetary() = safeLaunch {
        setUiState(BaseViewState.Loading)
        val params = GetPlanetary.Params(count = 20)
        execute(getPlanetary(params)) { planetaryList ->
            setUiState(BaseViewState.Data(PlanetaryViewState(planetaryList = planetaryList)))
        }
    }

    private fun onAddFavoritePlanetary(planetary: PlanetaryDto) = safeLaunch {
        val params = AddPlanetaryFavorite.Params(planetary = planetary)
        call(addPlanetaryFavorite(params))
    }

    private fun onLoadFavoritesPlanetary() = safeLaunch {
        call(getPlanetaryFavorites(Unit)) {
            if (it.isEmpty()) {
                setUiState(BaseViewState.Empty)
            } else {
                setUiState(BaseViewState.Data(PlanetaryViewState(favorPlanetaryList = it)))
            }
        }
    }

    private fun onDeleteFavoritePlanetary(date: String) = safeLaunch {
        call(deletePlanetaryFavorite(DeletePlanetaryFavorite.Params(date))) {
            onTriggerEvent(PlanetaryEvent.LoadFavoritesPlanetary)
        }
    }

    private fun onOrderPlanetary(orderType: ReorderType, planetary: List<PlanetaryDto>) {
        when (orderType) {
            ReorderType.ORDER_BY_TITLE -> {
                setUiState(BaseViewState.Data(PlanetaryViewState(planetaryList = planetary.sortedBy { it.title })))
            }
            ReorderType.ORDER_BY_DATE -> {
                val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                setUiState(BaseViewState.Data(PlanetaryViewState(planetaryList = planetary.sortedByDescending {
                    LocalDate.parse(it.date, dateFormatter)
                })))
            }
        }
    }

    fun setUiState(state: BaseViewState<PlanetaryViewState>) = safeLaunch {
        setState(state)
    }

}

enum class ReorderType(val value: String) {
    ORDER_BY_TITLE("Reorder by title"),
    ORDER_BY_DATE("Reorder by date");
}