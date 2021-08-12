package com.haydnjm.stocks.database

import android.content.Context
import com.haydnjm.db.TriggersDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(TriggersDatabase.Schema, context, "triggers.db")
    }
}