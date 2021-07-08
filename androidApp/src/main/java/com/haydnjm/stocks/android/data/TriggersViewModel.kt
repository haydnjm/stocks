package com.haydnjm.stocks.android.data

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.haydnjm.stocks.repository.TriggersRepository
import com.haydnjm.stocks.remote.Stock

val initialTriggers = mutableListOf<Trigger>(
    Trigger(
        timePeriod = 14,
        valueDelta = 20,
        stockName = "Apple",
        stockTicker = "[TEST1]"
    ),
    Trigger(
        timePeriod = 28,
        valueDelta = 5,
        stockName = "Google",
        stockTicker = "[TEST2]"
    ),
    Trigger(
        timePeriod = 3,
        valueDelta = 50,
        stockName = "GameStop",
        stockTicker = "[TEST3]"
    ),
)

class TriggersViewModel: ViewModel() {

    var triggers by mutableStateOf(initialTriggers)
        private set
    var editing by mutableStateOf(-1)
    var adding by mutableStateOf(false)
    private var triggersRepository: TriggersRepository = TriggersRepository()
    var newStockOptions by mutableStateOf(listOf<Stock>())


    fun addTrigger(trigger: Trigger) {
        triggers.add(trigger)
    }

    fun setEditingPosition(index: Int) {
        editing = index
    }

    fun setAddingState(newState: Boolean) {
        adding = newState
    }

    fun removeTrigger() {}
    fun editTrigger(index: Int, newTrigger: Trigger) {
        Log.i("LOGGR", "$index - $newTrigger")
        triggers[index] = newTrigger
    }

    fun getNasdaq(search: String?) {
        Log.i("LOGGR", "view model")
        triggersRepository.getNasdaq(search) {
            newStockOptions = it
        }
    }
}