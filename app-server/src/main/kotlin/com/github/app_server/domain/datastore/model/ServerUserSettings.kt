package com.github.app_server.domain.datastore.model

import com.github.datastore.model.SavedPreferences

data class ServerUserSettings(
    val clientIp : String? = null,
    val clientPort : Int? = null
) : SavedPreferences