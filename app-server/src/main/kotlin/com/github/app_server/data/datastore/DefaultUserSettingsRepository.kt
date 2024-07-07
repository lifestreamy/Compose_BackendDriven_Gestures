package com.github.app_server.data.datastore


import com.github.app_server.domain.datastore.UserSettingsRepository
import com.github.app_server.domain.datastore.model.ServerUserSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DefaultUserSettingsRepository @Inject constructor(private val dataStore: DefaultDataStore) :
    UserSettingsRepository {

    override val settingsFlow: Flow<ServerUserSettings> = dataStore.savedPreferencesFlow


    override suspend fun setSettings(newSettings: ServerUserSettings) =
        dataStore.replaceAllSavedPreferences(newSettings)

    override suspend fun getSettings(): ServerUserSettings = settingsFlow.first()

    override suspend fun resetSettings() = dataStore.clear()


    override suspend fun setServerIp(ip: String) =
        dataStore.writeValue(DefaultDataStoreSavedObject.SERVER_IP, ip)

    override suspend fun setServerPort(port: Int) =
        dataStore.writeValue(DefaultDataStoreSavedObject.SERVER_PORT, port)

}