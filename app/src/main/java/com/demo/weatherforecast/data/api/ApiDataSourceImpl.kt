package com.demo.weatherforecast.data.api

import android.content.Context
import androidx.lifecycle.liveData
import com.demo.weatherforecast.R
import com.demo.weatherforecast.data.model.ErrorMessage
import com.demo.weatherforecast.data.model.WeatherResource
import com.demo.weatherforecast.data.response.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ApiDataSourceImpl(private val context: Context, private val apiService: ApiService) : ApiDataSource {

    override fun fetchForecast(city: String) = liveData {
        emit(suspendCoroutine<WeatherResource> {
            apiService.fetchForecast(city).enqueue(object : Callback<WeatherResponse> {
                override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                    it.resume(response.body()!!)
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    it.resume(ErrorMessage(context.getString(R.string.err_weather_fetch)))
                }
        })})
    }

}