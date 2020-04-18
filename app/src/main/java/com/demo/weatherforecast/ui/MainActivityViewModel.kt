package com.demo.weatherforecast.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.demo.weatherforecast.data.api.ApiDataSource
import com.demo.weatherforecast.data.model.WeatherData
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import java.util.stream.Collectors


class MainActivityViewModel(private val apiDataSource: ApiDataSource) : ViewModel() {

    fun fetchForecast() = liveData {
        emitSource(apiDataSource.fetchForecast("London"))
    }

    fun transformMap(weatherDataList: List<WeatherData>): Map<Int, List<WeatherData>> {
        val map: Map<Int, List<WeatherData>> = weatherDataList.stream().collect(Collectors.groupingBy {
            LocalDateTime.ofInstant(Date(it.timestamp * 1000).toInstant(), ZoneId.systemDefault()).dayOfMonth
        })
        var index = 0
        return map.entries.fold(mutableMapOf()){ m, it ->
            m[index++] = it.value
            m
        }
    }

}