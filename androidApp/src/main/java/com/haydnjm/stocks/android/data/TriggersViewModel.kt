package com.haydnjm.stocks.android.data

import android.util.Log
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
    var editing by mutableStateOf(-1)

    fun addTrigger(trigger: Trigger) {
        triggers = triggers + listOf(trigger)
    }

    fun setEditingPosition(index: Int) {
        Log.i("DEBUG_VIEW_MODEL", "$index, $editing")
        editing = index
    }

    fun removeTrigger() {}
    fun editTrigger() {}
}