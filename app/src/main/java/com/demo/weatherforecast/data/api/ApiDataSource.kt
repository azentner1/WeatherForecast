package com.demo.weatherforecast.data.api

import androidx.lifecycle.LiveData
import com.demo.weatherforecast.data.model.WeatherResource


interface ApiDataSource {
    fun fetchForecast(city: String): LiveData<WeatherResource>
}