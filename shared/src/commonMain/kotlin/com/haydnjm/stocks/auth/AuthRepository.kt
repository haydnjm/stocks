package com.haydnjm.stocks.auth

import co.touchlab.kermit.Kermit
import com.haydnjm.stocks.database.Database
import com.haydnjm.stocks.database.DatabaseDriverFactory
import com.haydnjm.stocks.db.Session as CacheSession

class AuthRepository(databaseDriverFactory: DatabaseDriverFactory) {

    private val cache = Database(databaseDriverFactory)
    private val logger = Kermit()

    private fun decodeCacheSession(cacheSession: CacheSession): Session {
        return Session(
            id = cacheSession.id,
            expiration = cacheSession.expiration,
        )
    }

    fun getSession(): Session? {
        var session = cache.getSession()
        logger.d { "DB: auth repo session = $session" }

        return if(session == null) null else decodeCacheSession(session)
    }
}