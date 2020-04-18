package com.demo.weatherforecast.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.demo.weatherforecast.R
import com.demo.weatherforecast.data.model.WeatherData
import com.demo.weatherforecast.extensions.getDate
import com.demo.weatherforecast.extensions.getTimeInHours
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.weather_data_card.view.*
import kotlinx.android.synthetic.main.weather_list_item.view.*
import kotlin.math.roundToInt


class WeatherAdapter(private var weatherList: Map<Int, List<WeatherData>>, private val context: Context) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weatherData = weatherList[position]
        holder.itemView.llRoot.removeAllViews()
        holder.bind(weatherData)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    fun setData(weatherList: Map<Int, List<WeatherData>>) {
        this.weatherList = weatherList
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(weatherData: List<WeatherData>?) {
            weatherData?.let {
                for (data in it) {
                    itemView.txtDate.text = getDate(data.timestamp)
                    val view = LayoutInflater.from(context).inflate(R.layout.weather_data_card, null)
                    val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
                    val margin = context.resources.getDimensionPixelOffset(R.dimen.margin_8)
                    layoutParams.setMargins(margin, margin, margin, margin)
                    view.layoutParams = layoutParams
                    view.txtTime.text = getTimeInHours(data.timestamp)
                    view.txtTemperature.text = context.getString(R.string.temperature_value, data.temperature.temp.roundToInt().toString())
                    view.txtHumidityValue.text = context.getString(R.string.humidity_value, data.temperature.humidity.toString())
                    view.txtWindSpeedValue.text = context.getString(R.string.wind_speed_value, data.wind.speed.toString())
                    Picasso.get().load(context.getString(R.string.icon_url, data.weatherList[0].icon)).into(view.ivWeatherIcon)
                    itemView.llRoot.addView(view)
                }
            }
        }

    }

}