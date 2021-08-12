package com.haydnjm.stocks.remote

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*

// TODO: Make client each time an API is created?
private val nonStrictJson =
    kotlinx.serialization.json.Json { isLenient = true; ignoreUnknownKeys = true }
val Client = HttpClient {
    install(JsonFeature) {
        serializer = KotlinxSerializer(nonStrictJson)
    }
}