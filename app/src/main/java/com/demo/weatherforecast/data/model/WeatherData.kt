package com.demo.weatherforecast.data.model

import com.google.gson.annotations.SerializedName


data class WeatherData(@SerializedName("dt") val timestamp: Long,
                       @SerializedName("main") val temperature: Temperature,
                       @SerializedName("weather") val weatherList: List<Weather>,
                       @SerializedName("clouds") val clouds: Clouds,
                       @SerializedName("wind") val wind: Wind,
                       @SerializedName("dt_txt") val timestampText: String)