package com.adyen.android.assignment.data.model.local.planetary

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = PlanetaryEntity.TABLE_NAME)
data class PlanetaryEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = COLUMN_DATA) val date: String,
    @ColumnInfo(name = COLUMN_COPYRIGHT) val copyright: String,
    @ColumnInfo(name = COLUMN_EXPLANATION) val explanation: String,
    @ColumnInfo(name = COLUMN_HDURL) val hdurl: String,
    @ColumnInfo(name = COLUMN_MEDIA_TYPE) val media_type: String,
    @ColumnInfo(name = COLUMN_SERVICE_VERSION) val service_version: String,
    @ColumnInfo(name = COLUMN_TITLE) val title: String,
    @ColumnInfo(name = COLUMN_URL) val url: String,
) {
    companion object {
        const val TABLE_NAME = "planetary_favorite"
        const val COLUMN_COPYRIGHT = "copyright"
        const val COLUMN_DATA = "date"
        const val COLUMN_EXPLANATION = "explanation"
        const val COLUMN_HDURL = "hdurl"
        const val COLUMN_MEDIA_TYPE = "media_type"
        const val COLUMN_SERVICE_VERSION = "service_version"
        const val COLUMN_TITLE = "title"
        const val COLUMN_URL = "url"
    }
}