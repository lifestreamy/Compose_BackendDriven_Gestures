package com.github.app_server.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ReportsDao {

    @Insert
    suspend fun insertReport(report: GestureReportEntity)

    @Query("SELECT * FROM reports WHERE clientIp = :ip ORDER by timeStart DESC LIMIT 1")
    suspend fun getLatestReportByIp(ip : String) : GestureReportEntity

    @Query("SELECT * FROM reports WHERE clientIp = :ip ORDER by timeStart DESC")
    fun getAllReportsByIp(ip : String) : Flow<List<GestureReportEntity>>

    @Query("SELECT * FROM reports ORDER by timeStart DESC")
    fun getAllReports() : Flow<List<GestureReportEntity>>
}