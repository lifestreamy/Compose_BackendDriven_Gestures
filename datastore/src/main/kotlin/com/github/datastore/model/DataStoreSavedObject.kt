package com.github.datastore.model


import androidx.datastore.preferences.core.Preferences.Key

interface DataStoreSavedObject<T> {
    val key: Key<T>
    val defaultValue: T
}
