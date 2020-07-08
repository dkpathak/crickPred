package com.dk.crickprediction.di

import com.dk.crickprediction.data.AppDataHelper
import com.dk.crickprediction.data.AppDataManager
import com.dk.crickprediction.features.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

    val appModules = module {
        viewModel { MainViewModel(get()) }

        single<AppDataHelper> {
            AppDataManager()
        }
    }
