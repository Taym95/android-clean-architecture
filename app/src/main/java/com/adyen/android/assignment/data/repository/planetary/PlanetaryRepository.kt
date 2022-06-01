package com.adyen.android.assignment.data.repository.planetary

import androidx.annotation.VisibleForTesting
import com.adyen.android.assignment.data.local.planetary.PlanetaryFavoriteDao
import com.adyen.android.assignment.data.model.local.planetary.PlanetaryEntity
import com.adyen.android.assignment.data.remote.service.PlanetaryService
import javax.inject.Inject

class PlanetaryRepository
@Inject
constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val service: PlanetaryService,
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val dao: PlanetaryFavoriteDao
) {
    suspend fun getPlanetaryList(
        count: Int,
        apiKey: String,
    ) = service.getPlanetaryList(count , apiKey)

    suspend fun getPlanetary(
        date: String,
        apiKey: String,
    ) = service.getPlanetary(date , apiKey)


    suspend fun getFavoritePlanetaryList() = dao.getFavoriteList()

    suspend fun getFavoritePlanetary(favoriteDate: String) = dao.getFavorite(favoriteDate)

    suspend fun deleteFavoritePlanetaryByDate(favoriteDate: String) = dao.deleteFavoriteByDate(favoriteDate)

    suspend fun saveFavoritePlanetary(entity: PlanetaryEntity) = dao.insert(entity)
}