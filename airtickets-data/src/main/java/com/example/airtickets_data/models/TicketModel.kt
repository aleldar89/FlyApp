package com.example.airtickets_data.models

import com.google.gson.annotations.SerializedName

data class TicketModel(
    override val id: Int,
    val badge: String? = null,
    val price: PriceModel,
    @SerializedName("provider_name")
    val providerName: String,
    val company: String,
    val departure: FlightInfo,
    val arrival: FlightInfo,
    @SerializedName("has_transfer")
    val hasTransfer: Boolean,
    @SerializedName("has_visa_transfer")
    val hasVisaTransfer: Boolean,
    val luggage: Luggage?,
    @SerializedName("hand_luggage")
    val handLuggage: HandLuggage,
    @SerializedName("is_returnable")
    val isReturnable: Boolean,
    @SerializedName("is_exchangable")
    val isExchangable: Boolean
) : BaseModel

data class FlightInfo(
    val town: String,
    val date: String,
    val airport: String
)

data class Luggage(
    @SerializedName("has_luggage")
    val hasLuggage: Boolean,
    val price: PriceModel? = null
)

data class HandLuggage(
    @SerializedName("has_hand_luggage")
    val hasHandLuggage: Boolean,
    val size: String? = null
)