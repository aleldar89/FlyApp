package com.example.airtickets_feature.adapters

import androidx.core.content.ContextCompat
import com.example.domain.models.BaseModel
import com.example.domain.models.TicketOfferModel
import com.example.common_resources.R
import com.example.airtickets_feature.databinding.CardDirectFlightBinding
import com.example.airtickets_feature.utils.formatPrice
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun ticketOfferAdapterDelegate() =
    adapterDelegateViewBinding<com.example.domain.models.TicketOfferModel, com.example.domain.models.BaseModel, CardDirectFlightBinding>(
        { layoutInflater, parent ->
            CardDirectFlightBinding.inflate(layoutInflater, parent, false)
        }
    ) {
        bind {
            binding.apply {

                circle.setColorFilter(
                    ContextCompat.getColor(
                        context,
                        when (item.title) {
                            "Уральские авиалинии" -> R.color.red
                            "Победа" -> R.color.blue
                            "NordStar" -> R.color.white
                            else -> R.color.red
                        }
                    )
                )
                txtCompanyName.text = item.title
                txtTimeShedule.text = item.timeRange.joinToString(" ")
                txtPrice.text =
                    context.resources.getString(R.string.value, formatPrice(item.price.value))
            }
        }
    }