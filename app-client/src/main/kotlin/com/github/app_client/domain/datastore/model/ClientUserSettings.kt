package com.github.app_client.domain.datastore.model

import com.github.datastore.model.SavedPreferences

data class ClientUserSettings(
    val serverIP : String? = null,
    val serverPort : Int? = null
) : SavedPreferences