package com.github.network.server

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.routing.routing
import io.ktor.server.websocket.*
import io.ktor.websocket.CloseReason
import io.ktor.websocket.close
import kotlinx.serialization.json.Json
import java.time.Duration

//fun Application.configureSockets() {
//    install(WebSockets) {
//        contentConverter = KotlinxWebsocketSerializationConverter(Json)
//        pingPeriod = Duration.ofSeconds(5)
//        timeout = Duration.ofSeconds(3)
//        maxFrameSize = Long.MAX_VALUE
//        masking = false
//    }
//
//    routing {
//        webSocket("/tasks") {
//
//                sendSerialized("")
//
//            close(CloseReason(CloseReason.Codes.NORMAL, "All done"))
//        }
//    }
//}