package com.example.airtickets_feature.utils

import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.abs
import kotlin.math.round

fun formatPrice(price: Int): String {
    return String.format(Locale.US, "%d %03d", price / 1000, price % 1000)
}

fun calculateHoursDifference(departureDate: String, arrivalDate: String): Double {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val departure = dateFormat.parse(departureDate)
    val arrival = dateFormat.parse(arrivalDate)
    val hoursDifferenceMillis = arrival.time - departure.time
    val hoursDifference = hoursDifferenceMillis / (1000.0 * 60 * 60)
    return round(abs(hoursDifference) * 10) / 10 // Округляем до одного знака после запятой
}

fun convertTime(inputTime: String): String {
    val inputFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val outputFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    val date = inputFormatter.parse(inputTime)
    return outputFormatter.format(date)
}

//val baseDiffCallback = object : DiffUtil.ItemCallback<BaseModel>() {
//    override fun areItemsTheSame(oldItem: BaseModel, newItem: BaseModel): Boolean =
//        oldItem.id == newItem.id
//
//    @SuppressLint("DiffUtilEquals")
//    override fun areContentsTheSame(oldItem: BaseModel, newItem: BaseModel): Boolean =
//        oldItem == newItem
//}