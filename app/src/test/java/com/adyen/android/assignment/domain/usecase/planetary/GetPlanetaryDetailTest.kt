package com.adyen.android.assignment.domain.usecase.planetary

import com.adyen.android.assignment.data.repository.planetary.PlanetaryRepository
import com.adyen.android.assignment.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetPlanetaryDetailTest : MockkUnitTest() {

    @MockK(relaxed = true)
    lateinit var planetaryRepo: PlanetaryRepository

    @SpyK
    @InjectMockKs
    private lateinit var getPlanetaryDetail: GetPlanetaryDetail

    @Test
    fun verifyExecute() = runTest {
        val date = "2022-05-31"

        val params = GetPlanetaryDetail.Params(date = date)
        getPlanetaryDetail.invoke(params)

        coVerify { getPlanetaryDetail.invoke(any()) }
    }

    @Test
    fun collectExecute() = runTest {
        val date = "2022-05-31"

        val params = GetPlanetaryDetail.Params(date = date)
        getPlanetaryDetail.invoke(params).single()

        coVerify { planetaryRepo.getPlanetary(date = date) }
    }
}