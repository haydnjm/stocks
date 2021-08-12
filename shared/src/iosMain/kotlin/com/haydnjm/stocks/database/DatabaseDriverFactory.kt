package com.haydnjm.stocks.database

import com.haydnjm.db.TriggersDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(TriggersDatabase.Schema, "triggers.db")
    }
}