package com.adyen.android.assignment.data.remote.di

import com.adyen.android.assignment.BuildConfig
import com.adyen.android.assignment.utils.base.app.NetworkConfig
import com.adyen.android.assignment.testutils.RobolectricTest
import org.junit.Assert
import org.junit.Test

class RemoteModuleTest : RobolectricTest() {

    private lateinit var remoteModule: RemoteModule
    private lateinit var networkConfig: NetworkConfig

    override fun onCreate() {
        super.onCreate()
        networkConfig = object : NetworkConfig() {
            override fun baseUrl(): String = BuildConfig.NASA_BASE_URL
            override fun timeOut(): Long = 30L
        }
        remoteModule = RemoteModule()
    }

    @Test
    fun verifyBaseUrl() {
        val baseUrl = remoteModule.provideBaseUrl(networkConfig)
        Assert.assertEquals(baseUrl, BuildConfig.NASA_BASE_URL)
    }

//    @Test
//    fun verifyProvidePlanetaryService() {
//        val baseUrl = remoteModule.provideBaseUrl(networkConfig)
//        val httpClient = mockk<OkHttpClient>()
//        val moshi = mockk<Moshi>()
//        val retrofit = mockk<Retrofit>()
//        val service = mockk<PlanetaryService>()
//        val serviceClassCaptor = slot<Class<*>>()
//
//        every { retrofit.create<PlanetaryService>(any()) } returns service
//
//        remoteModule.providePlanetaryService(baseUrl, httpClient, moshi)
//
//        verify { retrofit.create(capture(serviceClassCaptor)) }
//        Assert.assertEquals(PlanetaryService::class.java, serviceClassCaptor.captured)
//    }
}