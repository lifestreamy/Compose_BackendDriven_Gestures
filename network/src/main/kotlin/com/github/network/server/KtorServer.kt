package com.github.network.server

import com.github.network.model.Client
import com.github.network.model.Gesture
import com.github.network.model.GestureReport
import io.ktor.server.application.call
import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.coroutines.*

class KtorServer(private val serverPort: Int = 8080) {

    var isStarted : Boolean = false

    private val serverScope : CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val _clientHandlers: MutableMap<Client, ClientHandler> = mutableMapOf()
    val clientHandlers : Map<Client, ClientHandler>
        get() = _clientHandlers.toMap()


//    val a : DefaultWebSocketServerSession


//    fun initServer(){
//        if (!isStarted)
//    }

    fun resetServer(){

    }

    val s = embeddedServer(CIO, port = serverPort) {
        routing {
            get("/") {
                call.respondText("Hello, world!")
            }
        }
    }


    protected fun onClientConnected(newClient: Client) {
        if (!_clientHandlers.contains(newClient)) _clientHandlers.put(newClient,
            ClientHandler(scope = CoroutineScope(Job() +  Dispatchers.IO),newClient)
        ) else
            throw KtorServerException("Can't add client, already exists in the map")

    }

    protected fun onClientDisconnected(client: Client) {
        if (_clientHandlers.contains(client)) _clientHandlers.remove(client) else
            throw KtorServerException("Can't remove client, does not exist in the map")
    }

//    fun launchServer(ip: String, port: Int) : Boolean {
//
//    }

//    fun stopServer() : Boolean {
//
//    }

    fun processReportsOnConnected(lambda : (GestureReport) -> Unit) {

    }

    fun sendGesture(gesture: Gesture) {

    }

}

class KtorServerException(msg: String? = null) : Exception(msg)