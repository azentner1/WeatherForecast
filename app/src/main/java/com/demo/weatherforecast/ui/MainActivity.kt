package com.demo.weatherforecast.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.demo.weatherforecast.R
import com.demo.weatherforecast.data.model.ErrorMessage
import com.demo.weatherforecast.data.response.WeatherResponse
import com.demo.weatherforecast.ui.adapter.WeatherAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel: MainActivityViewModel by viewModel()

    private val weatherAdapter by lazy {
        WeatherAdapter(mapOf(), this@MainActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()
    }

    override fun onResume() {
        super.onResume()
        fetchWeather()
    }

    private fun initUi() {
        val snapHelper = LinearSnapHelper()
        rvWeatherList.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        snapHelper.attachToRecyclerView(rvWeatherList)
        rvWeatherList.adapter = weatherAdapter
    }

    private fun fetchWeather() {
        mainActivityViewModel.fetchForecast().observe(this@MainActivity, Observer {
            if (it == null) {
                showError(getString(R.string.err_unknown))
                return@Observer
            }
            when (it) {
                is WeatherResponse -> {
                    weatherAdapter.setData(mainActivityViewModel.transformMap(it.weatherDataList))
                }
                is ErrorMessage -> {
                    showError(it.errorMessage)
                }
            }
        })
    }

    private fun showError(errorMessage: String) {
        Snackbar.make(clRoot, errorMessage, Snackbar.LENGTH_SHORT).show()
    }
}
