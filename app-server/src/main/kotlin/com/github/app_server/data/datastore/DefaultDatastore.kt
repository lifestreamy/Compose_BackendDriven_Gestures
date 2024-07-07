package com.github.app_server.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.github.app_server.domain.datastore.model.ServerUserSettings
import com.github.datastore.CustomPreferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class DefaultDataStore(context: Context) : CustomPreferencesDataStore<ServerUserSettings>(context = context) {

    override val savedPreferencesFlow: Flow<ServerUserSettings>
        get() = datastore.data.map { preferences ->
            ServerUserSettings(
            serverIP = preferences.getOrDefault(DefaultDataStoreSavedObject.SERVER_IP),
            serverPort = preferences.getOrDefault(DefaultDataStoreSavedObject.SERVER_PORT)
        )
    }.catch { exception ->
        if (exception is IOException) {
            emit(ServerUserSettings())
        } else {
            throw exception
        }
    }

    override suspend fun replaceAllSavedPreferences(newPrefs: ServerUserSettings) {
        datastore.edit { mutablePreferences ->
            mutablePreferences.clear()
            mutablePreferences.replaceValue(DefaultDataStoreSavedObject.SERVER_IP, newPrefs.serverIP)
            mutablePreferences.replaceValue(DefaultDataStoreSavedObject.SERVER_PORT, newPrefs.serverPort)
        }
    }

}