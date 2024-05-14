package com.example.domain.models

data class TicketModel(
    override val id: Int,
    val badge: String? = null,
    val price: PriceModel,
    val providerName: String,
    val company: String,
    val departure: FlightInfoModel,
    val arrival: FlightInfoModel,
    val hasTransfer: Boolean,
    val hasVisaTransfer: Boolean,
    val luggage: LuggageModel,
    val handLuggage: HandLuggageModel,
    val isReturnable: Boolean,
    val isExchangable: Boolean
) : BaseModel

data class FlightInfoModel(
    val town: String,
    val date: String,
    val airport: String
)

data class LuggageModel(
    val hasLuggage: Boolean,
    val price: PriceModel? = null
)

data class HandLuggageModel(
    val hasHandLuggage: Boolean,
    val size: String? = null
)