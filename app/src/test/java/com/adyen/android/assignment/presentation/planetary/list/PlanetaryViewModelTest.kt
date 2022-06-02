package com.adyen.android.assignment.presentation.planetary.list

import app.cash.turbine.test
import com.adyen.android.assignment.domain.mockdata.MockPlanetaryData
import com.adyen.android.assignment.domain.usecase.planetary.GetPlanetary
import com.adyen.android.assignment.domain.usecase.planetary.favorite.AddPlanetaryFavorite
import com.adyen.android.assignment.domain.usecase.planetary.favorite.DeletePlanetaryFavorite
import com.adyen.android.assignment.domain.usecase.planetary.favorite.GetPlanetaryFavorites
import com.adyen.android.assignment.testutils.MockkUnitTest
import com.adyen.android.assignment.utils.base.mvvm.BaseViewState
import com.adyen.android.assignment.utils.network.DataState
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

class PlanetaryViewModelTest : MockkUnitTest() {

    @RelaxedMockK
    lateinit var getPlanetary: GetPlanetary

    @MockK(relaxed = true)
    lateinit var getPlanetaryFavorites: GetPlanetaryFavorites

    @MockK(relaxed = true)
    lateinit var deletePlanetaryFavorite: DeletePlanetaryFavorite

    @MockK(relaxed = true)
    lateinit var addPlanetaryFavorite: AddPlanetaryFavorite



    @SpyK
    @InjectMockKs
    lateinit var planetaryViewModel: PlanetaryViewModel

    @Test
    fun verifyOnTriggerEventLoadPlanetary() = runTest {
        coEvery { getPlanetary.invoke(any()) } returns flow {
            emit(DataState.Success(listOf(MockPlanetaryData.getPlanetaryDto())))
        }

        planetaryViewModel.onTriggerEvent(PlanetaryEvent.LoadPlanetary)

        coEvery  { getPlanetary.invoke(any()) }
    }


    @Test
    fun verifyOnTriggerEventGetPlanetaryFavorites() = runTest {
        planetaryViewModel.onTriggerEvent(PlanetaryEvent.LoadFavoritesPlanetary)

        coVerify { getPlanetaryFavorites.invoke(Unit) }
    }

    @Test
    fun verifyOnTriggerEventDeleteFavoritePlanetary() = runTest {
        val date = "2022-05-31"

        planetaryViewModel.onTriggerEvent(PlanetaryEvent.DeleteFavoritePlanetary(date))

        coVerify { deletePlanetaryFavorite(DeletePlanetaryFavorite.Params(date)) }
    }

    @Test
    fun verifyOnTriggerEventAddFavoritePlanetary() = runTest {
        val planetary = MockPlanetaryData.getPlanetaryDto()

        planetaryViewModel.onTriggerEvent(PlanetaryEvent.AddFavoritePlanetary(planetary))

        coVerify { addPlanetaryFavorite(AddPlanetaryFavorite.Params(planetary)) }
    }

    @Test
    fun verifyOnTriggerEventLoadPlanetary_CheckState() = runTest {
        val response = listOf(MockPlanetaryData.getPlanetaryDto())
        coEvery { getPlanetary(any()) } returns flow { emit(DataState.Success(response)) }

        planetaryViewModel.onTriggerEvent(PlanetaryEvent.LoadPlanetary)

        planetaryViewModel.uiState.test {
            awaitItem().apply {
                Truth.assertThat(this).isNotNull()
                Truth.assertThat(this).isInstanceOf(BaseViewState::class.java)
            }
        }
    }
}