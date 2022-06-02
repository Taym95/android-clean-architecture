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

class GetPlanetaryTest : MockkUnitTest() {

    @MockK(relaxed = true)
    lateinit var repository: PlanetaryRepository

    @SpyK
    @InjectMockKs
    private lateinit var getPlanetary: GetPlanetary

    @Test
    fun verifyExecute() = runTest {
        val params = GetPlanetary.Params(count = 5)

        getPlanetary.invoke(params).single()

        coVerify { repository.getPlanetaryList(count = 5) }
    }
}