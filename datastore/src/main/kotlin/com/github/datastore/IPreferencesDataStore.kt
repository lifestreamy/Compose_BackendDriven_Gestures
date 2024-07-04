package com.github.datastore

import com.github.datastore.model.DataStoreSavedObject
import com.github.datastore.model.SavedPreferences
import kotlinx.coroutines.flow.Flow

interface IPreferencesDataStore<P : SavedPreferences> {

    val savedPreferencesFlow: Flow<P>

    suspend fun <T> readValue(key: DataStoreSavedObject<T>): T?

    suspend fun <T> readValueOrDefault(key: DataStoreSavedObject<T>): T

    suspend fun <T> writeValue(
        key: DataStoreSavedObject<T>,
        value: T
    )

    suspend fun clear()
}