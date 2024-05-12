package com.example.airtickets_data.models

import com.google.gson.annotations.SerializedName

data class TicketOfferModel(
    override val id: Int,
    val title: String,
    @SerializedName("time_range")
    val timeRange: List<String>,
    val price: PriceModel
) : BaseModel

