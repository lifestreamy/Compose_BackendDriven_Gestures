package com.github.app_server.data.network

import com.github.network.domain.model.Gesture
import com.github.network.domain.model.GestureReport
import com.github.network.domain.server.KtorServer

class KtorServerImpl : KtorServer() {

    override var isStarted: Boolean
        get() = TODO("Not yet implemented")
        set(value) {}
    override var isConnected: Boolean
        get() = TODO("Not yet implemented")
        set(value) {}

    override fun launchServer(
        ip: String,
        port: Int
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun stopServer(): Boolean {
        TODO("Not yet implemented")
    }

    override fun processReportsOnConnected(lambda: (GestureReport) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun sendGesture(gesture: Gesture) {
        TODO("Not yet implemented")
    }
}