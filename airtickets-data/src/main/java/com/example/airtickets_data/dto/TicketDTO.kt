package com.example.airtickets_data.dto

import com.google.gson.annotations.SerializedName

data class TicketDTO(
    val id: Int,
    val badge: String? = null,
    val price: PriceDTO,
    @SerializedName("provider_name")
    val providerName: String,
    val company: String,
    val departure: FlightInfoDTO,
    val arrival: FlightInfoDTO,
    @SerializedName("has_transfer")
    val hasTransfer: Boolean,
    @SerializedName("has_visa_transfer")
    val hasVisaTransfer: Boolean,
    val luggage: LuggageDTO,
    @SerializedName("hand_luggage")
    val handLuggage: HandLuggageDTO,
    @SerializedName("is_returnable")
    val isReturnable: Boolean,
    @SerializedName("is_exchangable")
    val isExchangable: Boolean
)

data class FlightInfoDTO(
    val town: String,
    val date: String,
    val airport: String
)

data class LuggageDTO(
    val hasLuggage: Boolean,
    val price: PriceDTO? = null
)

data class HandLuggageDTO(
    val hasHandLuggage: Boolean,
    val size: String? = null
)