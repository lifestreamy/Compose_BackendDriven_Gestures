package com.github.app_client.presentation

import com.github.app_client.domain.datastore.model.ClientUserSettings

data class ClientState(
    val isTaskRunning : Boolean = false,
    val savedSettings: ClientUserSettings = ClientUserSettings()
)


