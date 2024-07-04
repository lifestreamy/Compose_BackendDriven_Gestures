package com.github.app_server.presentation

import androidx.lifecycle.ViewModel
import com.github.app_server.data.db.ReportsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ServerViewModel @Inject constructor(private val reportsRepository: ReportsRepositoryImpl) : ViewModel(){

    private val _serverState : MutableStateFlow<ServerState> = MutableStateFlow(ServerState())
    val serverState = _serverState.asStateFlow()

}