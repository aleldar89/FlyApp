package com.example.airtickets_feature.adapters

import androidx.core.content.ContextCompat
import com.example.airtickets_data.models.BaseModel
import com.example.airtickets_data.models.TicketOfferModel
import com.example.common_resources.R
import com.example.airtickets_feature.databinding.CardDirectFlightBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun ticketOfferAdapterDelegate() =
    adapterDelegateViewBinding<TicketOfferModel, BaseModel, CardDirectFlightBinding>(
        { layoutInflater, parent ->
            CardDirectFlightBinding.inflate(layoutInflater, parent, false)
        }
    ) {
        bind {
            binding.apply {
                circle.setColorFilter(
                    ContextCompat.getColor(
                        context,
                        when (binding.companyName.text.toString()) {
                            "Уральские авиалинии" -> R.color.red
                            "Победа" -> R.color.blue
                            "NordStar" -> R.color.white
                            else -> R.color.red
                        }
                    )
                )
                companyName.text = item.title
                timeShedule.text = item.timeRange.joinToString(" ")
                value.text = context.resources.getString(
                    R.string.value,
                    item.price.value.toString()
                )
            }
        }
    }