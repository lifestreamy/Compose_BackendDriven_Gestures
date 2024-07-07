package com.github.app_client.presentation

import androidx.lifecycle.ViewModel
import com.github.app_client.domain.datastore.UserSettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


class ClientViewModel @Inject constructor(private val serSettingsRepository: UserSettingsRepository) : ViewModel() {

    private val _clientState : MutableStateFlow<ClientState> = MutableStateFlow(ClientState())
    val clientState = _clientState.asStateFlow()


}

