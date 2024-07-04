package com.github.app_server.domain.datastore

import com.github.app_server.domain.datastore.model.ServerUserSettings
import kotlinx.coroutines.flow.Flow

interface UserSettingsRepository {

    val settingsFlow : Flow<ServerUserSettings>

    suspend fun setSettings(newSettings: ServerUserSettings)

    suspend fun getSettings() : ServerUserSettings

}