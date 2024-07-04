package com.github.app_client.data.network

import com.github.network.domain.client.KtorClient
import com.github.network.domain.model.Gesture
import com.github.network.domain.model.GestureReport

class KtorClientImpl : KtorClient() {

    override var isStarted: Boolean
        get() = TODO("Not yet implemented")
        set(value) {}
    override var isConnected: Boolean
        get() = TODO("Not yet implemented")
        set(value) {}

    override fun startClient(): Boolean {
        TODO("Not yet implemented")
    }

    override fun stopClient(): Boolean {
        TODO("Not yet implemented")
    }

    override fun processGesturesOnConnected(lambda: (Gesture) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun sendGestureReport(report: GestureReport): Boolean {
        TODO("Not yet implemented")
    }
}