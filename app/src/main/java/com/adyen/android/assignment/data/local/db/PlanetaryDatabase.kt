package com.adyen.android.assignment.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.adyen.android.assignment.data.local.converter.StringListConverter
import com.adyen.android.assignment.data.local.planetary.PlanetaryFavoriteDao
import com.adyen.android.assignment.data.model.local.planetary.PlanetaryEntity

@Database(
    entities = [PlanetaryEntity::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(StringListConverter::class)
abstract class PlanetaryDatabase : RoomDatabase() {
    abstract fun planetaryFavoriteDao(): PlanetaryFavoriteDao
}