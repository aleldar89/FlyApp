package com.example.domain.models

data class TicketOfferModel(
    override val id: Int,
    val title: String,
    val timeRange: List<String>,
    val price: PriceModel
) : BaseModel

