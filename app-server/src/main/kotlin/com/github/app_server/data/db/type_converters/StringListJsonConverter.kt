package com.github.app_server.data.db.type_converters

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal class StringListJsonConverter {

    @TypeConverter
    fun restoreList(listOfString: String): List<String> {
        return Json.Default.decodeFromString<List<String>>(listOfString)
    }

    @TypeConverter
    fun saveList(listOfString: List<String>): String {
        return Json.Default.encodeToString<List<String>>(listOfString)
    }

}