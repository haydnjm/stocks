package com.haydnjm.stocks.android.data

// Stand-in for stock IDs from API
enum class Stock {
    Tesla,
    Google,
    Apple,
    LuluLemon,
    GameStop,
}

data class Trigger(
    val timePeriod: Int,
    val valueDelta: Int,
    val stock: Stock,
)