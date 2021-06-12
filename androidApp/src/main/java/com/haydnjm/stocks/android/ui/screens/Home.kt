package com.haydnjm.stocks.android.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.haydnjm.stocks.android.data.Trigger
import com.haydnjm.stocks.android.data.TriggersViewModel
import com.haydnjm.stocks.android.data.initialTriggers
import com.haydnjm.stocks.android.ui.theme.Padding
import org.koin.androidx.compose.getViewModel

@Composable
fun Home() {

    val triggersViewModel = getViewModel<TriggersViewModel>()
    val triggers = triggersViewModel.triggers

    Surface {
        Text(text = "Editing poisition: ${triggersViewModel.editing}")
        TriggerList(
            triggers = triggers,
            setEditing = triggersViewModel::setEditingPosition
        )
    }


}

@Composable
fun TriggerList(
    triggers: List<Trigger>,
    setEditing: (Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        triggers.forEachIndexed { index, trigger ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Padding.medium)
            ) {
                Text(
                    text = trigger.stock.toString(),
                    modifier = Modifier.weight(2f)
                )
                Text(
                    text = "${trigger.valueDelta}%",
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = trigger.timePeriod.toString(),
                    modifier = Modifier.weight(1f)
                )
                Button(onClick = { setEditing(index) }) {
                    Text(text = "Edit")
                }
            }
        }
    }
}

@Preview("TriggerList preview")
@Composable
private fun TriggerListPreview() {
    TriggerList(
        triggers = initialTriggers,
        setEditing = { n -> Unit }
    )
//    { index ->
//        Log.i("INDEX!", index.toString())
//    }
}