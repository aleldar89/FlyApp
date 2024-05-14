package com.example.airtickets_data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.FlightInfoModel
import com.example.domain.models.HandLuggageModel
import com.example.domain.models.LuggageModel
import com.example.domain.models.PriceModel

@Entity
data class TicketEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
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
)