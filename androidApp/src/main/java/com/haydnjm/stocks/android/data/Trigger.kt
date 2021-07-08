package com.haydnjm.stocks.android.data

data class Trigger(
    val timePeriod: Int,
    val valueDelta: Int,
    val stockName: String,
    val stockTicker: String
)