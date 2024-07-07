package com.github.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.http.HttpMethod
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.routing.routing
import io.ktor.server.websocket.WebSockets
import io.ktor.server.websocket.webSocket
import io.ktor.websocket.*
import kotlinx.coroutines.*
import org.junit.Assert.assertEquals
import org.junit.Test

class SimpleWebSocketClientServerTest {

    private val scope = CoroutineScope(Dispatchers.IO)

    private val host = "0.0.0.0"
    private val serverPort = 8080

    private val message = "Hello, Server!"


    @Test
    fun testAsyncServerClientInteraction()  {
        scope.launch {
            println("creating server")
            val server = embeddedServer(
                io.ktor.server.cio.CIO,
                host = host,
                port = serverPort,
                configure = { -> },
            ) {
                testWebsocketsModule()
            }

            println("server created, starting server")
            server.start(wait = false)
            println("started server")
        }

        scope.launch {
            println("creating client")

            val client = HttpClient(CIO) {
                install(io.ktor.client.plugins.websocket.WebSockets) {
                }
            }
            println("client created")

            client.webSocket(
                method = HttpMethod.Get,
                host = host,
                port = serverPort,
                path = "/messages"
            ) {
                println("sending message to server")
                send(message)
                println("message sent")
            }
        }
        Thread.sleep(1000)
    }


    fun Application.testWebsocketsModule() {
        install(WebSockets)
        routing {
            webSocket("/messages") {
                send("Hello!")
                for (frame in incoming) {
                    frame as? Frame.Text ?: continue
                    val receivedText = frame.readText()
                    assertEquals(receivedText, message)
                    if (receivedText.equals(
                            message,
                            ignoreCase = true
                        )) {
                        println("Client said hello!")
                        close(
                            CloseReason(
                                CloseReason.Codes.NORMAL,
                                "Client said hello!"
                            )
                        )
                        println("Server closed")
                    }
                }
            }
        }
    }
}