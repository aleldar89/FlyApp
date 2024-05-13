package com.example.airtickets_feature.adapters

import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.example.airtickets_data.models.BaseModel
import com.example.airtickets_data.models.TicketModel
import com.example.airtickets_feature.databinding.CardTicketBinding
import com.example.airtickets_feature.utils.calculateHoursDifference
import com.example.airtickets_feature.utils.convertTime
import com.example.airtickets_feature.utils.formatPrice
import com.example.common_resources.R
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun ticketAdapterDelegate() =
    adapterDelegateViewBinding<TicketModel, BaseModel, CardTicketBinding>(
        { layoutInflater, parent ->
            CardTicketBinding.inflate(layoutInflater, parent, false)
        }
    ) {
        bind {
            binding.apply {

                if (item.badge.isNullOrBlank()) {
                    badge.visibility = GONE
                    (container.layoutParams as ViewGroup.MarginLayoutParams).topMargin =
                        context.resources.getDimensionPixelSize(R.dimen.space_16)
                } else {
                    badge.visibility = VISIBLE
                    txtBadge.text = item.badge
                    (container.layoutParams as ViewGroup.MarginLayoutParams).topMargin =
                        context.resources.getDimensionPixelSize(R.dimen.space_24)
                }

                txtPrice.text =
                    context.resources.getString(R.string.value, formatPrice(item.price.value))
                txtDepartureData.text = convertTime(item.departure.date)
                txtArrivalData.text = convertTime(item.arrival.date)
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