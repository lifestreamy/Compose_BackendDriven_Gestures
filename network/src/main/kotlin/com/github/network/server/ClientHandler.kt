package com.github.network.server

import com.github.network.model.Client
import com.github.network.util.cancelChildren
import io.ktor.utils.io.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ClientHandler(
    private val scope: CoroutineScope,
    private val client: Client
) {


    fun startSendingGestures() {
        scope.launch {

        }

    }


    fun stopSendingGestures() {
        scope.cancelChildren(CancellationException("Stopped sending gestures"))
    }
}