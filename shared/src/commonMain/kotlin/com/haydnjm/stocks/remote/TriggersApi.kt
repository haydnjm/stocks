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
    private val client: HttpClient = Client,
    private val baseUrl: String = Constants.HOST
) {

    suspend fun getNasdaq(search: String? = ""): List<Stock> {
        return client.get("$baseUrl/get-nasdaq?search=${search ?: ""}")
    }
}