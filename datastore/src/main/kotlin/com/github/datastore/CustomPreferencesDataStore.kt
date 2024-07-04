package com.github.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStoreFile
import com.github.datastore.model.DataStoreSavedObject
import com.github.datastore.model.SavedPreferences
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

/**
 * @author lifestreamy (Tim K.)
 */
abstract class CustomPreferencesDataStore<P : SavedPreferences> :
    IPreferencesDataStore<P> {

    protected companion object {
        const val NAME = "DEFAULT_DATASTORE"

        fun <T> Preferences.getOrDefault(obj: DataStoreSavedObject<T>): T =
            this[obj.key] ?: obj.defaultValue

        /**
         * if null is passed, delete the value.
         * */
        fun <T1, T2 : T1?> MutablePreferences.replaceValue(
            obj: DataStoreSavedObject<T1>,
            value: T2
        ) {
            value?.let {
                this[obj.key] = value
            } ?: this[obj.key]?.apply { remove(obj.key) }
        }
    }

    protected val datastore: DataStore<Preferences>


    constructor(context: Context) {
        datastore =
            PreferenceDataStoreFactory.create(produceFile = { context.preferencesDataStoreFile(NAME) })
    }

    /**
     * Use to instantiate a separate [CustomPreferencesDataStore] with a name.
     * There is no DataStoreOwner or management strategy available for now, so you should manage DataStores
     * and avoid possible name conflicts by yourself.
     * */
    constructor(
        context: Context,
        dataStoreName: String
    ) {
        datastore =
            PreferenceDataStoreFactory.create(produceFile = { context.preferencesDataStoreFile(dataStoreName) })
    }


    /**
     *  Override to define the replacement strategy.
     *  [Preferences] from the DataStore package should be cleared and replaced
     *  with new ones, based on the passed [SavedPreferences], which is a
     *  custom marker interface that marks a custom model (data class).
     *  This model (<P : SavedPreferences>) represents stored data in your dataStore (inheritor of [CustomPreferencesDataStore] ).
     * */
    abstract suspend fun replaceAllSavedPreferences(newPrefs: P)


    final override suspend fun <T> readValue(key: DataStoreSavedObject<T>): T? {
        return datastore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[key.key]
        }.first()
    }

    final override suspend fun <T> readValueOrDefault(key: DataStoreSavedObject<T>): T =
        readValue(key) ?: key.defaultValue

    final override suspend fun <T> writeValue(
        key: DataStoreSavedObject<T>,
        value: T
    ) {
        datastore.edit { preferences ->
            preferences[key.key] = value
        }
    }

    final override suspend fun clear() {
        datastore.edit { preferences -> preferences.clear() }
    }
}