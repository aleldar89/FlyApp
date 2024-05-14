package com.example.domain.models

data class OfferModel(
    override val id: Int,
    val title: String,
    val town: String,
    val price: PriceModel
) : BaseModel