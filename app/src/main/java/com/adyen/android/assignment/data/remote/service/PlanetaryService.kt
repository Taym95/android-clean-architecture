package com.adyen.android.assignment.data.remote.service

import com.adyen.android.assignment.data.model.remote.planetary.PlanetaryInfo
import retrofit2.http.*

interface PlanetaryService {
    @GET(PLANETARY)
    suspend fun getPlanetaryList(
        @Query("count") count: Int,
        @Query("api_key") apiKey: String,
    ): List<PlanetaryInfo>


    @GET(PLANETARY)
    suspend fun getPlanetary(
        @Query("date") date: String,
        @Query("api_key") apiKey: String,
    ): PlanetaryInfo


    companion object {
        const val PLANETARY = "planetary/apod"
    }
}