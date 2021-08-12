package com.haydnjm.stocks.database

import com.squareup.sqldelight.db.SqlDriver

@Suppress("NO_ACTUAL_FOR_EXPECT") // https://stackoverflow.com/questions/54838225/expected-class-has-no-actual-declaration
expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}