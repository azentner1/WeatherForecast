package com.demo.weatherforecast.extensions

import java.text.SimpleDateFormat
import java.util.*

fun getTimeInHours(timestamp: Long) : String {
    val format = SimpleDateFormat("HH:mm")
    return format.format(Date(timestamp * 1000))
}

fun getDate(timestamp: Long) : String {
    val format = SimpleDateFormat("dd.MM.yyyy")
    return format.format(Date(timestamp * 1000))
}