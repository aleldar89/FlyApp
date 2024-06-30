package com.example.airtickets_feature.utils

import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.abs
import kotlin.math.round

fun ruLocale() = Locale("ru", "RU")

fun formatPrice(price: Int) =
    String.format(ruLocale(), "%d %03d", price / 1000, price % 1000)

fun dateFormat() = SimpleDateFormat("dd MMM", ruLocale())

fun dateFullFormat() = SimpleDateFormat("dd MMMM", ruLocale())

fun dayOfWeekFormat() = SimpleDateFormat("EE", ruLocale())

fun calculateHoursDifference(departureDate: String, arrivalDate: String): Double {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", ruLocale())
    val departure = dateFormat.parse(departureDate)
    val arrival = dateFormat.parse(arrivalDate)
    val hoursDifferenceMillis = arrival.time - departure.time
    val hoursDifference = hoursDifferenceMillis / (1000.0 * 60 * 60)
    return round(abs(hoursDifference) * 10) / 10
}

fun convertTime(inputTime: String): String {
    val inputFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", ruLocale())
    val outputFormatter = SimpleDateFormat("HH:mm", ruLocale())
    val date = inputFormatter.parse(inputTime)
    return outputFormatter.format(date)
}