package com.adyen.android.assignment.data.local.converter

import androidx.room.TypeConverter
import com.adyen.android.assignment.utils.extension.fromJson
import com.adyen.android.assignment.utils.extension.toJson

class StringListConverter {
    @TypeConverter
    fun toListOfStrings(stringValue: String): List<String>? {
        return stringValue.fromJson()
    }

    @TypeConverter
    fun fromListOfStrings(listOfString: List<String>?): String {
        return listOfString.toJson()
    }
}