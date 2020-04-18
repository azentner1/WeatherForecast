package com.demo.weatherforecast.data.response

import com.demo.weatherforecast.data.model.WeatherData
import com.demo.weatherforecast.data.model.WeatherResource
import com.google.gson.annotations.SerializedName


data class WeatherResponse(@SerializedName("cod") val code: String, @SerializedName("cnt") val count: Int,
                           @SerializedName("list") val weatherDataList: List<WeatherData>) : WeatherResource