package com.github.app_client.presentation.ui

sealed interface MainScreenEvent {
    data class SetTaskState(val isActive : Boolean) : MainScreenEvent
}