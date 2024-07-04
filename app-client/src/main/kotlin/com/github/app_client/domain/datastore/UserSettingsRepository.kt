package com.github.app_client.domain.datastore

import com.github.app_client.domain.datastore.model.ClientUserSettings
import kotlinx.coroutines.flow.Flow

interface UserSettingsRepository {

    val settingsFlow : Flow<ClientUserSettings>

    suspend fun setSettings(newSettings: ClientUserSettings)

    suspend fun getSettings() : ClientUserSettings

    suspend fun setServerIp(ip : String)

    suspend fun setServerPort(port : Int)

    suspend fun resetSettings()

}