package com.github.app_server.data.db

import androidx.room.Entity
import androidx.room.TypeConverters
import com.github.app_server.data.db.type_converters.InstantConverter
import com.github.network.domain.model.GestureReport
import kotlinx.datetime.Instant

@Entity(
    tableName = "reports",
    primaryKeys = ["clientIp"]
)
data class GestureReportEntity(
    val clientIp: String,
    val timeStart: Instant,
    val timeEnd: Instant,
    val observedSiteNames: List<String>
)

fun GestureReportEntity.asNetworkModel() = GestureReport(
    clientIp = clientIp,
    timeStart = timeStart,
    timeEnd = timeEnd,
    observedSiteNames = observedSiteNames
)

fun GestureReport.asDatabaseEntity() = GestureReportEntity(
    clientIp = clientIp,
    timeStart = timeStart,
    timeEnd = timeEnd,
    observedSiteNames = observedSiteNames
)


