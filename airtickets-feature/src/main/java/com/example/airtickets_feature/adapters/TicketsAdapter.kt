package com.example.airtickets_feature.adapters

import android.view.View.GONE
import android.view.View.VISIBLE
import com.example.airtickets_data.models.BaseModel
import com.example.airtickets_data.models.TicketModel
import com.example.airtickets_feature.databinding.CardTicketBinding
import com.example.common_resources.R
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

fun ticketAdapterDelegate() =
    adapterDelegateViewBinding<TicketModel, BaseModel, CardTicketBinding>(
        { layoutInflater, parent ->
            CardTicketBinding.inflate(layoutInflater, parent, false)
        }
    ) {
        bind {
            binding.apply {
                if (item.badge.isNullOrBlank())
                    badge.visibility = GONE
                else {
                    badge.visibility = VISIBLE
                    txtBadge.text = item.badge
                }


                txtPrice.text = item.price.value.toString()
                txtDepartureData.text = item.departure.date
                txtArrivalData.text = item.arrival.date
                txtDepartureAirport.text = item.departure.airport
                txtArrivalAirport.text = item.arrival.airport

                txtHours.text = context.resources.getString(
                    R.string.hours,
                    calculateHoursDifference(item.departure.date, item.arrival.date).toString()
                )

                if (item.hasTransfer)
                    txtWithoutTransfer.visibility = VISIBLE
                else
                    txtWithoutTransfer.visibility = GONE
            }
        }
    }

fun calculateHoursDifference(departureDate: String, arrivalDate: String): Long {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val departure = dateFormat.parse(departureDate)
    val arrival = dateFormat.parse(arrivalDate)
    return abs((arrival.time - departure.time) / (1000 * 60 * 60))
}