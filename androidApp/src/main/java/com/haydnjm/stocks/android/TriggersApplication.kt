package com.haydnjm.stocks.android

import android.app.Application
import com.haydnjm.stocks.android.di.appModule
import org.koin.core.context.startKoin

class TriggersApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
        }
    }
}