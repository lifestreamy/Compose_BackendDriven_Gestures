package com.github.network.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.cancellation.CancellationException

fun CoroutineScope.cancelChildren(cause: CancellationException? = null) =
    this.coroutineContext.cancelChildren()
