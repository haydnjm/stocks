package com.haydnjm.stocks.android.ui.screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.haydnjm.stocks.android.data.Trigger
import com.haydnjm.stocks.android.data.TriggersViewModel
import com.haydnjm.stocks.android.data.initialTriggers
import com.haydnjm.stocks.android.ui.theme.Padding
import org.koin.androidx.compose.getViewModel

@Composable
fun Home() {

    val triggersViewModel = getViewModel<TriggersViewModel>()
    val triggers = triggersViewModel.triggers
    val editing = triggersViewModel.editing

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(Padding.medium),
            horizontalAlignment = Alignment.End,
        ) {
            TriggerList(
                triggers = triggers,
                setEditing = triggersViewModel::setEditingPosition
            )
            Button(onClick = { triggersViewModel.setAddingState(true) }) {
                Text("Add new trigger")
            }
        }
        TriggerForm(
            show = editing > -1,
            initialData = if (editing > -1) triggers[editing] else null
        ) { triggersViewModel.setEditingPosition(-1) }
    }
}

@Composable
fun TriggersHeader() {
    Column {
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Padding.small)
        ) {
            Text(
                text = "Stock",
                style = MaterialTheme.typography.body2,
                modifier = Modifier.weight(2f)
            )
            Text(
                text = "Value delta",
                style = MaterialTheme.typography.body2,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Time period (days)",
                style = MaterialTheme.typography.body2,
                modifier = Modifier.weight(1f)
            )
            Box(
                modifier = Modifier.weight(1f),
            )
        }
        Divider()
    }
}

@Composable
fun TriggerList(
    triggers: List<Trigger>,
    setEditing: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Padding.small)
    ) {
        TriggersHeader()
        triggers.forEachIndexed { index, trigger ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = Padding.small)
            ) {
                Text(
                    text = trigger.stock.toString(),
                    style = MaterialTheme.typography.h6,
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
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = { setEditing(index) }) {
                    Text(text = "Edit")
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TriggerForm(
    show: Boolean,
    initialData: Trigger?,
    close: () -> Unit
) {
    AnimatedVisibility(
        visible = show,
        enter = slideInVertically(
            initialOffsetY = { fullHeight -> fullHeight },
            animationSpec = tween(durationMillis = 200, easing = LinearOutSlowInEasing)
        ),
        exit = slideOutVertically(
            targetOffsetY = { fullHeight -> fullHeight },
            animationSpec = tween(durationMillis = 200, easing = LinearOutSlowInEasing)
        )
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .shadow(elevation = 20.dp),
            shape = RoundedCornerShape(
                topStart = 20.dp,
                topEnd = 20.dp,
                bottomStart = 0.dp,
                bottomEnd = 0.dp
            )
        ) {
            Column(
                modifier = Modifier.padding(Padding.medium)
            ) {
                Text(text = "This is the trigger form!")
                Button(onClick = close) {
                    Text(text = "Close edit window")
                }
                if (initialData != null) {
                    Text(text = initialData.stock.toString(), style=MaterialTheme.typography.h6)
                    TriggerPropertyInput(label = "Price delta", value = initialData.valueDelta)
                    TriggerPropertyInput(label = "Time delta", value = initialData.timePeriod)
                }
            }
        }
    }
}

@Composable
fun TriggerPropertyInput(
    label: String,
    value: Int,
) {
    Row(
        modifier = Modifier
            .padding(Padding.small)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(text = label)
        Text(text = value.toString())
    }
}

@Preview("TriggerHeader preview")
@Composable
private fun TriggerHeaderPreview() {
    TriggersHeader()
}

@Preview("TriggerList preview")
@Composable
private fun TriggerListPreview() {
    TriggerList(
        triggers = initialTriggers,
        setEditing = { n -> Unit }
    )
}

@Preview("TriggerForm preview")
@Composable
private fun TriggerFormPreview() {
    TriggerForm(true, null) { }
}