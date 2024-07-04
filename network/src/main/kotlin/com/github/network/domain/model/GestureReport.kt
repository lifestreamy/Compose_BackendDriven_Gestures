package com.github.network.domain.model

import kotlinx.datetime.Instant


data class GestureReport(
    val clientIp: String,
    val timeStart : Instant,
    val timeEnd : Instant,
    val observedSiteNames : List<String>
)
