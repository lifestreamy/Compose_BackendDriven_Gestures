package com.github.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import kotlinx.coroutines.test.runTest
import org.junit.Test

/**
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class KtorClientUnitTests {

    private companion object {
        const val BASE_URL = "https://httpbin.org"
        const val GET_UUID = "$BASE_URL/uuid"
    }

    @Test
    fun testKtorClientSimpleGetRequest() {
        runTest {
            val client = HttpClient(CIO)
            println("Getting response from $GET_UUID ")
            val data = client.get(GET_UUID).body<String>()
            println("testKtorClientSimpleGetRequest: $data")
        }
    }

}