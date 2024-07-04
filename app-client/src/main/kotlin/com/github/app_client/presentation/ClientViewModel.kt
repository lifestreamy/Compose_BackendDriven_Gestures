package com.github.app_client.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class ClientViewModel : ViewModel() {

    private val _clientState : MutableStateFlow<ClientState> = MutableStateFlow(ClientState())
    val clientState = _clientState.asStateFlow()

    

}

