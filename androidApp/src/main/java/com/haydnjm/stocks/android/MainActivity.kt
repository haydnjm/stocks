package com.haydnjm.stocks.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.haydnjm.stocks.Greeting
import com.haydnjm.stocks.android.ui.StocksApp

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StocksApp()
        }
    }
}
