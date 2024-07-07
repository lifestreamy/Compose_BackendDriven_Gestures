package com.github.network.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class Gesture(
    val isDirectionUp: Boolean,
    val length: Int
)

fun Gesture.encodeToJsonString(): String = Json.Default.encodeToString(this)

object GestureDecoder

fun GestureDecoder.decodeGestureFromJsonString(string: String): Gesture =
    Json.Default.decodeFromString<Gesture>(string)