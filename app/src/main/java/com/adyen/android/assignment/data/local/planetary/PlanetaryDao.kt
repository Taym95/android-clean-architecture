package com.adyen.android.assignment.data.local.planetary

import androidx.room.Dao
import androidx.room.Query
import com.adyen.android.assignment.data.model.local.planetary.PlanetaryEntity
import com.adyen.android.assignment.utils.room.dao.BaseDao

@Dao
interface PlanetaryFavoriteDao : BaseDao<PlanetaryEntity> {
    @Query("SELECT * FROM ${PlanetaryEntity.TABLE_NAME}")
    suspend fun getFavoriteList(): List<PlanetaryEntity>

    @Query("SELECT * FROM ${PlanetaryEntity.TABLE_NAME} WHERE date = :favoriteDate")
    suspend fun getFavorite(favoriteDate: String): PlanetaryEntity?

    @Query("DELETE FROM ${PlanetaryEntity.TABLE_NAME}")
    suspend fun deleteFavoriteList()

    @Query("DELETE FROM ${PlanetaryEntity.TABLE_NAME} WHERE date = :favoriteDate")
    suspend fun deleteFavoriteByDate(favoriteDate: String)
}