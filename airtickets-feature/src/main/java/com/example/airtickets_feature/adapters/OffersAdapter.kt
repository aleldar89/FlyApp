package com.example.airtickets_feature.adapters

import com.example.domain.models.BaseModel
import com.example.domain.models.OfferModel
import com.example.airtickets_feature.R
import com.example.airtickets_feature.databinding.CardMusicRouteBinding
import com.example.airtickets_feature.loadImage
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun offerAdapterDelegate() =
    adapterDelegateViewBinding<OfferModel, BaseModel, CardMusicRouteBinding>(
        { layoutInflater, parent ->
            CardMusicRouteBinding.inflate(layoutInflater, parent, false) }
    ) {
        bind {
            binding.apply {

                imageView.loadImage(
                    when (item.id) {
                        1 -> R.drawable.one
                        2 -> R.drawable.two
                        3 -> R.drawable.three
                        else -> com.example.common_resources.R.drawable.image_placeholder
                    }
                )

                title.text = item.title
                town.text = item.town
                price.text = context.resources.getString(
                    com.example.common_resources.R.string.price,
                    item.price.value.toString()
                )
            }
        }
    }