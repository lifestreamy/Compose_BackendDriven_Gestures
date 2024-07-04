package com.github.app_client.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.github.app_client.domain.datastore.model.ClientUserSettings
import com.github.datastore.CustomPreferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class DefaultDataStore(context: Context) : CustomPreferencesDataStore<ClientUserSettings>(context = context) {

    override val savedPreferencesFlow: Flow<ClientUserSettings>
        get() = datastore.data.map { preferences ->
        ClientUserSettings(
            serverIP = preferences.getOrDefault(DefaultDataStoreSavedObject.SERVER_IP),
            serverPort = preferences.getOrDefault(DefaultDataStoreSavedObject.SERVER_PORT)
        )
    }.catch { exception ->
        if (exception is IOException) {
            emit(ClientUserSettings())
        } else {
            throw exception
        }
    }

    override suspend fun replaceAllSavedPreferences(newPrefs: ClientUserSettings) {
        datastore.edit { mutablePreferences ->
            mutablePreferences.clear()
            mutablePreferences.replaceValue(DefaultDataStoreSavedObject.SERVER_IP, newPrefs.serverIP)
            mutablePreferences.replaceValue(DefaultDataStoreSavedObject.SERVER_PORT, newPrefs.serverPort)
        }
    }

}