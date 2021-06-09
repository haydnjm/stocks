package com.haydnjm.stocks.android.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.haydnjm.stocks.android.data.Trigger
import com.haydnjm.stocks.android.data.TriggersViewModel
import com.haydnjm.stocks.android.data.initialTriggers
import org.koin.androidx.compose.getViewModel

@Composable
fun Home() {

    val triggersViewModel = getViewModel<TriggersViewModel>()
    val triggers = triggersViewModel.triggers


}

@Composable
fun TriggerList(triggers: List<Trigger>) {
    Column {
        triggers.forEach { trigger ->
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = trigger.timePeriod.toString())
                Text(text = trigger.timePeriod.toString())
                Text(text = trigger.timePeriod.toString())
            }
        }
    }
}

@Preview("TriggerList preview")
@Composable
private fun TriggerListPreview() {
    TriggerList(initialTriggers)
}