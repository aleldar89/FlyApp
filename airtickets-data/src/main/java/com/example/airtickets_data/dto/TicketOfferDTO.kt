package com.example.airtickets_data.dto

import com.google.gson.annotations.SerializedName

data class TicketOfferDTO(
    val id: Int,
    val title: String,
    @SerializedName("time_range")
    val timeRange: List<String>,
    val price: PriceDTO
)

