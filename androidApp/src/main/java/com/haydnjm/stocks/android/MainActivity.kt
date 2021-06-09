package com.haydnjm.stocks.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.haydnjm.stocks.Greeting
import com.haydnjm.stocks.android.ui.StocksApp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StocksApp()
        }
    }
}
