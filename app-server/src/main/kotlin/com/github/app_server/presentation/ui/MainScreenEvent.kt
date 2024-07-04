package com.github.app_server.presentation.ui

sealed interface MainScreenEvent {
    data class SetTaskState(val isActive : Boolean) : MainScreenEvent
}