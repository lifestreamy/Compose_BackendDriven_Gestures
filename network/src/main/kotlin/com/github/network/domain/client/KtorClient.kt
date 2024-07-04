package com.github.network.domain.client

import com.github.network.domain.model.Gesture
import com.github.network.domain.model.GestureReport

abstract class KtorClient {

    protected abstract var isStarted : Boolean
    protected abstract var isConnected : Boolean

    abstract fun startClient() : Boolean

    abstract fun stopClient() : Boolean

    abstract fun processGesturesOnConnected(lambda : (Gesture) -> Unit)

    abstract fun sendGestureReport(report: GestureReport) : Boolean

}