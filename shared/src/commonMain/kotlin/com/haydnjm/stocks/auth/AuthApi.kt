package com.haydnjm.stocks.auth

import co.touchlab.kermit.Kermit
import com.haydnjm.stocks.remote.Client
import com.haydnjm.stocks.remote.Constants
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.Serializable

@Serializable
data class Session(
    val id: String,
    val expiration: Long
)

data class User(
    val id: String,
    val firstName: String,
    val lastName: String,
)

class AuthApi(
    private val client: HttpClient = Client,
    private val baseUrl: String = Constants.HOST,
) {
    private val logger = Kermit()

    suspend fun loginWithEmail(email: String, password: String): User? {
        return try {
            client.post("$baseUrl/login") {
                body = email
            }
        } catch(e: Exception) {
            logger.d { "Unable to log in: $e" }
            return null
        }
    }
    suspend fun signUpWithEmail(email: String, password: String): String {
        return "This is the session id";
    }
    suspend fun touchSession(sessionId: String) {

    }
}