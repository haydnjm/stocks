package com.haydnjm.stocks.remote

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.serialization.Serializable

@Serializable
data class Stock(
    val symbol: String,
    val name: String,
    val marketCategory: String,
    val testIssue: String,
    val financialStatus: String,
    val roundLotSize: String,
    val etf: String,
    val nextShares: String,
)

class TriggersApi(
    private val baseUrl: String = Constants.HOST
) {

    // TODO: Make client each time an API is created?
    private val nonStrictJson =
        kotlinx.serialization.json.Json { isLenient = true; ignoreUnknownKeys = true }
    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(nonStrictJson)
        }
    }

    suspend fun getNasdaq(search: String? = ""): List<Stock> {
        return client.get("$baseUrl/get-nasdaq?search=${search ?: ""}")
    }
}