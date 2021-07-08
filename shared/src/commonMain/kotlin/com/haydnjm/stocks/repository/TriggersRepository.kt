package com.haydnjm.stocks.repository

import com.haydnjm.stocks.remote.Stock
import com.haydnjm.stocks.remote.TriggersApi
import io.ktor.client.features.logging.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class TriggersRepository {

    private val coroutineScope : CoroutineScope = MainScope()
    private val triggersApi = TriggersApi()

    // TODO: Use DB here instead of callback
    fun getNasdaq(search: String?, cb: (List<Stock>) -> Unit) {
        coroutineScope.launch {
            val stocks = triggersApi.getNasdaq(search)
            cb(stocks)
        }
    }
}
