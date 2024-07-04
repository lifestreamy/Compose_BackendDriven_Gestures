package com.github.app_client.data

import com.github.app_client.data.datastore.DefaultDataStore
import com.github.app_client.data.datastore.DefaultDataStoreSavedObject
import com.github.app_client.domain.datastore.UserSettingsRepository
import com.github.app_client.domain.datastore.model.ClientUserSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DefaultUserSettingsRepository @Inject constructor(private val dataStore: DefaultDataStore) :
    UserSettingsRepository {

    override val settingsFlow: Flow<ClientUserSettings> = dataStore.savedPreferencesFlow


    override suspend fun setSettings(newSettings: ClientUserSettings) =
        dataStore.replaceAllSavedPreferences(newSettings)

    override suspend fun getSettings(): ClientUserSettings = settingsFlow.first()

    override suspend fun resetSettings() = dataStore.clear()


    override suspend fun setServerIp(ip: String) =
        dataStore.writeValue(DefaultDataStoreSavedObject.SERVER_IP, ip)

    override suspend fun setServerPort(port: Int) =
        dataStore.writeValue(DefaultDataStoreSavedObject.SERVER_PORT, port)

}