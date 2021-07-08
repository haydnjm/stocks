package com.haydnjm.stocks.android.ui.screens

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.haydnjm.stocks.android.ui.theme.Padding
import org.koin.androidx.compose.getViewModel
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.haydnjm.stocks.android.data.*

@Composable
fun Home() {

    val triggersViewModel = getViewModel<TriggersViewModel>()
    val triggers = triggersViewModel.triggers
    val editing = triggersViewModel.editing
    val adding = triggersViewModel.adding

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            Text(text = "My Triggers", style = MaterialTheme.typography.h2)
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
        }
    }

    // Edit and close form
    fun editTrigger(trigger: Trigger) {
        triggersViewModel.editTrigger(editing, trigger)
        triggersViewModel.setEditingPosition(-1)
    }

    // Editing form
    TriggerFormContainer(
        show = editing > -1,
        trigger = if (editing > -1) triggers[editing] else null,
        close = { triggersViewModel.setEditingPosition(-1) }
    ) { trigger -> editTrigger(trigger) }

    // Adding form
    TriggerFormContainer(
        show = adding,
        trigger = null,
        close = { triggersViewModel.setAddingState(false) }
    ) { trigger -> Log.i("New trigger", trigger.toString()) }

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
                    text = trigger.stockName.substringBefore(" - "),
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
fun TriggerFormContainer(
    show: Boolean,
    trigger: Trigger?,
    close: () -> Unit,
    submitForm: (Trigger) -> Unit,
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
        ),
    ) {
        TriggerForm(
            trigger = trigger,
            handleSubmit = submitForm,
            close = close
        )
    }
}

@Composable
fun TriggerForm(
    trigger: Trigger?,
    handleSubmit: (trigger: Trigger) -> Unit,
    close: () -> Unit
) {
    val viewModel = getViewModel<TriggersViewModel>()
    val priceDelta = remember {
        mutableStateOf<String>(trigger?.valueDelta?.toString() ?: "0")
    }
    val timeDelta = remember {
        mutableStateOf<String>(trigger?.timePeriod?.toString() ?: "0")
    }
    val ticker = remember {
        mutableStateOf<String>(trigger?.stockTicker ?: "")
    }
    val stockName = viewModel.newStockOptions.find { it.symbol == ticker.value }?.name ?: ""
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp)
            .shadow(elevation = 20.dp),
        shape = RoundedCornerShape(
            topStart = 20.dp,
            topEnd = 20.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp
        )
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.primaryVariant)
                .padding(Padding.medium),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Button(onClick = close) {
                        Text(text = "Close")
                    }
                }
                if (stockName != "") {
                    Text(
                        text = stockName,
                        style = MaterialTheme.typography.h5
                    )
                    TriggerPropertyInput(
                        label = "Price delta (%)",
                        value = priceDelta.value
                    ) { output ->
                        priceDelta.value = output.filter { it.isDigit() }
                    }
                    TriggerPropertyInput(label = "Time delta (days)", value = timeDelta.value) { output ->
                        timeDelta.value = output.filter { it.isDigit() }
                    }

                    // Submit button
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.End
                    ) {
                        Button(
                            onClick = {

                                if (priceDelta.value != "" && timeDelta.value != "" && stockName != "") {
                                    handleSubmit(
                                        Trigger(
                                            timePeriod = timeDelta.value.toInt(),
                                            valueDelta = priceDelta.value.toInt(),
                                            stockTicker = ticker.value,
                                            stockName = stockName
                                        )
                                    )
                                }
                            },
                            modifier = Modifier.padding(Padding.medium)
                        ) {
                            Text("Save", style = MaterialTheme.typography.h5)
                        }
                    }
                } else {
                    StockSelector {
                        ticker.value = it
                    }
                }
            }
        }
    }
}

@Composable
fun StockSelector(
    handleSelect: (String) -> Unit
) {

    val triggersViewModel = getViewModel<TriggersViewModel>()
    val search = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TextField(
            value = search.value,
            onValueChange = { value ->
                search.value = value
                triggersViewModel.getNasdaq(search.value)
            }
        )
        LazyColumn{
            items(triggersViewModel.newStockOptions) { stock ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = Padding.medium, bottom = Padding.medium)
                        .clickable {
                            handleSelect(stock.symbol)
                        }
                ) {
                    Text(text = "[${stock.symbol}] - ${stock.name.substringBefore(" - ")}")
                }
                Divider()
            }
        }
    }
}

@Composable
fun TriggerPropertyInput(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
) {
    fun increment(input: String, direction: Int): String {
        val num = input.toInt()
        return (num + direction).toString()
    }
    Row(
        modifier = modifier
            .padding(top = Padding.small, bottom = Padding.small)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = label, style = MaterialTheme.typography.subtitle1)
        Row {
            Button(onClick = { onValueChange(increment(value, -1)) }) {
                Text(
                    text = "-",
                    style = MaterialTheme.typography.h4
                )
            }
            TextField(
                modifier = Modifier
                    .requiredWidth(75.dp)
                    .padding(start = 5.dp, end = 5.dp),
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                ),
                value = value,
                onValueChange = onValueChange,
            )
            Button(onClick = { onValueChange(increment(value, 1)) }) {
                Text(
                    text = "+",
                    style = MaterialTheme.typography.h4
                )
            }
        }
    }
}

/**
 * Add drag to dismiss to modifier
 * Come back later - not working on layered surfaces
 */
//private fun Modifier.dragToDismiss(
//): Modifier = composed {
//
//    val offsetY = remember {
//        Animatable(0f)
//    }
//
//    pointerInput(Unit) {
//        coroutineScope {
//            while (true) {
//                val pointerId = awaitPointerEventScope { awaitFirstDown().id }
//                awaitPointerEventScope {
//                    verticalDrag(pointerId) { change ->
//                        launch {
//                            val positionChange = change.positionChange().y;
//                            Log.i("POS_CHANGE", positionChange.toString())
//                            offsetY.snapTo(offsetY.value + positionChange)
//                        }
//                    }
//                }
//
//                launch {
//
//                }
//            }
//        }
//    }
//        .offset {
//            // Apply offset to element
//            IntOffset(0, offsetY.value.roundToInt())
//        }
//
//}

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
    TriggerForm(
        trigger = null,
        { trigger -> Log.i("LOGGR", trigger.toString()) }
    ) { }
}