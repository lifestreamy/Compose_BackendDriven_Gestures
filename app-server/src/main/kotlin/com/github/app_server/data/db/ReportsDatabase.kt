package com.github.app_server.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.app_server.data.db.type_converters.InstantConverter
import com.github.app_server.data.db.type_converters.StringListJsonConverter

@Database(
    entities = [GestureReportEntity::class],
    exportSchema = true,
    version = ReportsDatabase.LATEST_VERSION
)
@TypeConverters(InstantConverter::class, StringListJsonConverter::class)
internal abstract class ReportsDatabase : RoomDatabase() {

    internal companion object {
        const val LATEST_VERSION = 1
        const val DB_NAME = "REPORTS_DATABASE"
    }

    abstract fun reportsDao(): ReportsDao

}