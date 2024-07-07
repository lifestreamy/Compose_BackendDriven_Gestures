package com.github.app_server.data.datastore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.github.datastore.model.DataStoreSavedObject

sealed class DefaultDataStoreSavedObject<T>(
    override val key: Preferences.Key<T>, override val defaultValue: T
) : DataStoreSavedObject<T> {

    data object SERVER_IP : DefaultDataStoreSavedObject<String>(stringPreferencesKey("SERVER_IP"), defaultValue = "0.0.0.0")
    data object SERVER_PORT : DefaultDataStoreSavedObject<Int>(intPreferencesKey("SERVER_PORT"), defaultValue = 0)
}