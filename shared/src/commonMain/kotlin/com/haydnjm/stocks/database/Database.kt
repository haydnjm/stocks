package com.haydnjm.stocks.database

import co.touchlab.kermit.Kermit
import com.haydnjm.db.TriggersDatabase
import com.haydnjm.stocks.db.Session

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = TriggersDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.triggersQueries
    private val logger: Kermit = Kermit()

    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeSessions()
        }
    }

    internal fun saveSession(session: Session) {
        dbQuery.saveSession(session.id, session.expiration)
    }

    internal fun getSession(): Session? {
        logger.d { "DB: Fetching session" }
        return try {
            dbQuery.getSession().executeAsOne()
        } catch (e: Exception) {
            if (e is NullPointerException) {
                logger.d { "DB: Null pointer found" }
                null
            } else {
                logger.d { "DB: Other error found $e" }
                throw(e)
            }
        }
    }
}