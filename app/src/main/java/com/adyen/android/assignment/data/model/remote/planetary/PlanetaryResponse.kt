package com.adyen.android.assignment.data.model.remote.planetary

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlanetaryResponse(
    @Json(name = "results") val results: List<PlanetaryInfo>?
)