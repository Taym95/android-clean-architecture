package com.adyen.android.assignment.presentation.planetary.detail

import app.cash.turbine.test
import com.adyen.android.assignment.data.model.dto.planetary.PlanetaryDto
import com.adyen.android.assignment.domain.usecase.planetary.GetPlanetaryDetail
import com.adyen.android.assignment.testutils.MockkUnitTest
import com.adyen.android.assignment.utils.base.mvvm.BaseViewState
import com.adyen.android.assignment.utils.network.DataState
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException

class PlanetaryDetailViewModelTest : MockkUnitTest() {

    @RelaxedMockK
    lateinit var getPlanetaryDetail: GetPlanetaryDetail

    @SpyK
    @InjectMockKs
    lateinit var planetaryDetailViewModel: PlanetaryDetailViewModel

    @Test
    fun verifyOnTriggerEventLoadDetailWithDate() = runTest {
        val date = "2022-05-31"

        planetaryDetailViewModel.onTriggerEvent(PlanetaryDetailEvent.LoadPlanetaryDetail(date))

        coVerify { getPlanetaryDetail.invoke(GetPlanetaryDetail.Params(date = date)) }
    }

    @Test
    fun verifyOnTriggerEventLoadDetail_CheckState() = runTest {
        val date = "2022-05-31"
        val dto = mockk<PlanetaryDto>()
        val params = GetPlanetaryDetail.Params(date = date)
        coEvery { getPlanetaryDetail.invoke(params) } returns flow {
            emit(DataState.Success(dto))
        }

        planetaryDetailViewModel.onTriggerEvent(PlanetaryDetailEvent.LoadPlanetaryDetail(date))

        planetaryDetailViewModel.uiState.test {
            awaitItem().apply {
                Truth.assertThat(this).isNotNull()
                Truth.assertThat(this).isInstanceOf(BaseViewState::class.java)
            }
        }
    }

    @Test
    fun verifyOnTriggerEventLoadDetail_CheckError() = runTest {
        val date = "2022-05-31"
        coEvery { getPlanetaryDetail(any()) } returns flow {
            emit(DataState.Error(IOException("BOOM!")))
        }

        planetaryDetailViewModel.onTriggerEvent(PlanetaryDetailEvent.LoadPlanetaryDetail(date))

        coVerify(exactly = 1) { getPlanetaryDetail(any()) }
        confirmVerified(getPlanetaryDetail)
    }
}