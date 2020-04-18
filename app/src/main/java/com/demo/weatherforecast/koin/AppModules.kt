package com.demo.weatherforecast.koin

import com.demo.weatherforecast.data.api.ApiDataSource
import com.demo.weatherforecast.data.api.ApiDataSourceImpl
import com.demo.weatherforecast.data.api.ApiService
import com.demo.weatherforecast.ui.MainActivityViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModules = module {

    // api
    single<ApiService> { ApiService() }

    // data sources
    single<ApiDataSource> { ApiDataSourceImpl(get(), get()) }

    // view models
    viewModel { MainActivityViewModel(get()) }
}