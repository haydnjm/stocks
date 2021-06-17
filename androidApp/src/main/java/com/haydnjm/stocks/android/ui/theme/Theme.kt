package com.haydnjm.stocks.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

private val LightThemeColors = lightColors(
    primary = purple200,
    primaryVariant = purple500,
    secondary = green500,
    secondaryVariant = green300,
    surface = blue900,
    onSurface = blue100,
)

private val DarkThemeColours = darkColors()

object Padding {
    val small = 8.dp
    val medium = 16.dp
    val large = 32.dp
    val extraLarge = 64.dp
}

@Composable
fun StocksTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if(darkTheme) DarkThemeColours else LightThemeColors,
        typography = StocksTypography,
        content = content,
    )
}