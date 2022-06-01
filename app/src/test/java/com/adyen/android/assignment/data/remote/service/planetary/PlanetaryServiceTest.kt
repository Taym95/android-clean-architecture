package com.adyen.android.assignment.data.remote.service.planetary

import com.adyen.android.assignment.BuildConfig
import com.adyen.android.assignment.data.remote.service.PlanetaryService
import com.adyen.android.assignment.data.remote.service.mock.MockResponses
import com.adyen.android.assignment.testutils.ServiceTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test


class PlanetaryServiceTest : ServiceTest<PlanetaryService>(PlanetaryService::class) {

    override val baseUrl: String
        get() = BuildConfig.NASA_BASE_URL

    @Test
    fun requestLiveGetPlanetaryList() = runTest {
        val response = serviceLive.getPlanetaryList(count = 5, apiKey = BuildConfig.API_KEY )
        Assert.assertEquals(response.size, 5)
    }

    @Test
    fun requestLiveGetPlanetary() = runTest {
        val response = serviceLive.getPlanetary(date = "2022-05-31", apiKey = BuildConfig.API_KEY)
        Assert.assertEquals("2022-05-31", response.date)
        Assert.assertEquals("https://apod.nasa.gov/apod/image/2205/FalconSun_Cain_960.jpg", response.url)
        Assert.assertEquals("Rocket Transits Rippling Sun", response.title)
    }


    @Test
    fun requestGetPlanetary() = runTest {
        enqueueResponse(MockResponses.GetPlanetary.STATUS_200)
        serviceMock.getPlanetaryList(count = 5, apiKey = "apiKey")
        val request = mockWebServer.takeRequest()
        Assert.assertEquals("GET", request.method)
        Assert.assertEquals("/planetary/apod?count=5&api_key=apiKey", request.path)
    }

    @Test
    fun responseGetPlanetary() = runTest {
        enqueueResponse(MockResponses.GetPlanetary.STATUS_200)
        val response = serviceMock.getPlanetaryList(count = 5, apiKey = "apiKey")
        Assert.assertEquals("2006-06-29", response.first().date)
        Assert.assertEquals("https://apod.nasa.gov/apod/image/0606/moonPL_jacques_c.jpg", response.first().url)
        Assert.assertEquals("Old Moon and Sister Stars", response.first().title)
    }
}