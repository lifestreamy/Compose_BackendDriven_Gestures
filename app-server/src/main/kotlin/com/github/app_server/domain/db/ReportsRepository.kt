package com.github.app_server.domain.db

import com.github.network.domain.model.GestureReport
import kotlinx.coroutines.flow.Flow

interface ReportsRepository {

    suspend fun insertReport(report: GestureReport)

    suspend fun getLatestReportForIP(ip: String) : GestureReport

    suspend fun getReportsForIP(ip: String) : Flow<List<GestureReport>>

    suspend fun getAllReports() : Flow<List<GestureReport>>
}