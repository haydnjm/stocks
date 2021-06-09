package com.haydnjm.stocks.android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

private val NavHeight = 56.dp;

@Composable
fun TopNav(navigate: (String) -> Unit) {
    NavBar(
        navigate = navigate,
        items = listOf(
            Routes.Account.name
        )
    )
}

@Composable
fun BottomNav(navigate: (String) -> Unit) {
    NavBar(
        navigate = navigate,
        items = listOf(
            Routes.Home.name,
            Routes.Triggers.name
        )
    )
}

@Composable
fun NavBar(items: List<String>, navigate: (String) -> Unit) {
    Surface(
        modifier = Modifier
            .height(NavHeight)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .selectableGroup(),
            horizontalArrangement = Arrangement.Start
        ) {
            items.forEach { item ->
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { navigate(item) },
                ) {
                    Text(text = item)
                }
            }
        }
    }
}

@Preview("Top nav preview")
@Composable
private fun TopNavPreview() {
    TopNav(navigate = {})
}

@Preview("Bottom nav preview")
@Composable
private fun BottomNavPreview() {
    BottomNav(navigate = {})
}