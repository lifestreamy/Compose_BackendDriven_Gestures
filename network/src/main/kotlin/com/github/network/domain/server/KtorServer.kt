package com.github.network.domain.server

import com.github.network.domain.model.Gesture
import com.github.network.domain.model.GestureReport

abstract class KtorServer {

    protected abstract var isStarted : Boolean
    protected abstract var isConnected : Boolean

    abstract fun launchServer(ip: String, port: Int) : Boolean

    abstract fun stopServer() : Boolean

    abstract fun processReportsOnConnected(lambda : (GestureReport) -> Unit)

    abstract fun sendGesture(gesture: Gesture)

}