package com.github.app_server.data.db

import com.github.app_server.domain.db.ReportsRepository
import com.github.network.model.GestureReport
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class ReportsRepositoryImpl @Inject constructor(private val repDao: ReportsDao) :
    ReportsRepository {

    override suspend fun insertReport(report: GestureReport) {
        repDao.insertReport(report.asDatabaseEntity())
    }

    override suspend fun getLatestReportForIP(ip: String): GestureReport =
        repDao.getLatestReportByIp(ip).asNetworkModel()

    override suspend fun getReportsForIP(ip: String): Flow<List<GestureReport>> =
        repDao.getAllReportsByIp(ip).mapLatest { reportsList ->
            reportsList.map { reportEntity ->
                reportEntity.asNetworkModel()
            }
        }.distinctUntilChanged()

    override suspend fun getAllReports(): Flow<List<GestureReport>> =
        repDao.getAllReports().mapLatest { reportsList ->
            reportsList.map { reportEntity ->
                reportEntity.asNetworkModel()
            }
        }.distinctUntilChanged()
}