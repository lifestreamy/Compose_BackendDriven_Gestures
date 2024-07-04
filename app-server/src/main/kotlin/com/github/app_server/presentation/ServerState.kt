package com.github.app_server.presentation

import com.github.app_server.domain.datastore.model.ServerUserSettings

data class ServerState(
    val isTaskRunning : Boolean = false,
    val savedSettings: ServerUserSettings = ServerUserSettings()
)
