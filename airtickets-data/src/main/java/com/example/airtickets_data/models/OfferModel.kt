package com.example.airtickets_data.models

data class OfferModel(
    override val id: Int,
    val title: String,
    val town: String,
    val price: PriceModel
) : BaseModel