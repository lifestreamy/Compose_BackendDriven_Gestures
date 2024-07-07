package com.github.network.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class GestureReport(
    val clientIp: String,
    val timeStart: Instant,
    val timeEnd: Instant,
    val observedSiteNames: List<String>
)


fun GestureReport.encodeToJsonString(): String = Json.Default.encodeToString(this)

object GestureReportDecoder

fun GestureReportDecoder.decodeGestureFromJsonString(string: String): GestureReport =
    Json.Default.decodeFromString<GestureReport>(string)