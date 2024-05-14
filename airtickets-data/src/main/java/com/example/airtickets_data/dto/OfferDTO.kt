package com.example.airtickets_data.dto

data class OfferDTO(
    val id: Int,
    val title: String,
    val town: String,
    val price: PriceDTO
)