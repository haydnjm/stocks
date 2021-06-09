package com.haydnjm.stocks.android.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

val initialTriggers = listOf(
    Trigger(
        timePeriod = 14,
        valueDelta = 20,
        stock = Stock.Apple
    ),
    Trigger(
        timePeriod = 28,
        valueDelta = 5,
        stock = Stock.Google
    ),
    Trigger(
        timePeriod = 3,
        valueDelta = 50,
        stock = Stock.GameStop
    ),
)

class TriggersViewModel: ViewModel() {

    var triggers by mutableStateOf(initialTriggers)
        private set

    fun addTrigger(trigger: Trigger) {
        triggers = triggers + listOf(trigger)
    }

    fun removeTrigger() {}
    fun editTrigger() {}
}