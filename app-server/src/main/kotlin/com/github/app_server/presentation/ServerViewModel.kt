package com.github.app_server.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.app_server.data.db.ReportsRepositoryImpl
import com.github.app_server.domain.datastore.UserSettingsRepository
import com.github.app_server.domain.datastore.model.ServerUserSettings
import com.github.app_server.presentation.ServerUiEvent.SaveNewSettings
import com.github.network.model.GestureReport
import com.github.network.server.KtorServer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ServerViewModel @Inject constructor(private val reportsRepository: ReportsRepositoryImpl, private val userSettingsRepository: UserSettingsRepository,
    /*private val gestureServerConnector: GestureServerConnector*/) : ViewModel(){

    private val _serverState : MutableStateFlow<ServerState> = MutableStateFlow(ServerState())
    val serverState = _serverState.asStateFlow()

    fun onEvent(event: ServerUiEvent){
        viewModelScope.launch {
            when (event) {
                is SaveNewSettings -> userSettingsRepository.setSettings(event.settings)
                is ServerUiEvent.SetServerState -> {}
            }
        }
    }

}

sealed interface ServerUiEvent {

    data class SaveNewSettings(val settings: ServerUserSettings) : ServerUiEvent

    data class SetServerState(val isActive: Boolean) : ServerUiEvent

}