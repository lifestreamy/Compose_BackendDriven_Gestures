package com.github.network

import com.github.network.model.*
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.http.HttpMethod
import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.routing.routing
import io.ktor.server.websocket.WebSockets
import io.ktor.server.websocket.webSocket
import io.ktor.websocket.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.coroutines.cancellation.CancellationException

class KtorWebSocketJsonGesturesTest {

    private val scope = CoroutineScope(Dispatchers.IO)

    private val host = "0.0.0.0"
    private val serverPort = 8080

    private var gestureToSend = Gesture(
        isDirectionUp = true,
        length = 123
    )

    private var gestureReportToSend = GestureReport(
        clientIp = "client.ip",
        timeStart = Instant.DISTANT_PAST,
        timeEnd = Instant.DISTANT_FUTURE,
        observedSiteNames = listOf(
            "a",
            "b",
            "c"
        )
    )

    @Test
    fun testServerClientJson() {
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
                    contentConverter = KotlinxWebsocketSerializationConverter(Json)
                }
            }
            println("client created")

            client.webSocket(
                method = HttpMethod.Get,
                host = host,
                port = serverPort,
                path = "/gestures"
            ) {
                incoming.receiveAsFlow().onEach { frame ->
                    if (frame is Frame.Text) try {
                        val gesture = GestureDecoder.decodeGestureFromJsonString(frame.readText())
                        println("Client received a Gesture: $gesture")
                        assert(gesture == gestureToSend){
                            "Received Gesture != sent gesture! "
                        }
                    } catch (e: Exception) {
                        println("Caught an exception in client incoming flow processing. \n $e")
                        if (e is CancellationException) throw e
                    }
                }.launchIn(scope)

                println("sending message to server")
                send(gestureReportToSend.encodeToJsonString())
                println("message sent")
            }
        }
        Thread.sleep(1000)
    }

    fun Application.testWebsocketsModule() {
        install(WebSockets)
        routing {
            webSocket("/gestures") {
                send(
                    gestureToSend.encodeToJsonString()
                )
                for (frame in incoming) {
                    frame as? Frame.Text ?: continue
                    val receivedGestureReport: GestureReport =
                        GestureReportDecoder.decodeGestureFromJsonString(frame.readText())
                    assertEquals(
                        receivedGestureReport,
                        gestureReportToSend
                    )
                    if (receivedGestureReport == gestureReportToSend) {
                        println("received gestureReport: $receivedGestureReport")
                        close(
                            CloseReason(
                                CloseReason.Codes.NORMAL,
                                "GestureReport delivered correctly"
                            )
                        )
                        println("Server closed")
                    }
                }
            }
        }
    }
}