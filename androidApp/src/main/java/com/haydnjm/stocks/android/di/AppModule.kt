package com.haydnjm.stocks.android.di

import com.haydnjm.stocks.android.data.TriggersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { TriggersViewModel() }
}