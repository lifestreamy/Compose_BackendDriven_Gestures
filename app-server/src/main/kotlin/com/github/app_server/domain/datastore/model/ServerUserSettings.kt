package com.github.app_server.domain.datastore.model

import com.github.datastore.model.SavedPreferences

data class ServerUserSettings(
    val serverIP : String? = null,
    val serverPort : Int? = null
) : SavedPreferences