package com.example.airtickets_data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.airtickets_data.models.FlightInfo
import com.example.airtickets_data.models.HandLuggage
import com.example.airtickets_data.models.Luggage
import com.example.airtickets_data.models.PriceModel
import com.example.airtickets_data.models.TicketModel

@Entity
data class TicketEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val badge: String? = null,
    val price: PriceModel,
    val providerName: String,
    val company: String,
    val departure: FlightInfo,
    val arrival: FlightInfo,
    val hasTransfer: Boolean,
    val hasVisaTransfer: Boolean,
    val luggage: Luggage?,
    val handLuggage: HandLuggage,
    val isReturnable: Boolean,
    val isExchangable: Boolean
) {

    fun entityToModel() = TicketModel(
        id = id,
        badge = badge,
        price = price,
        providerName = providerName,
        company = company,
        departure = departure,
        arrival = arrival,
        hasTransfer = hasTransfer,
        hasVisaTransfer = hasVisaTransfer,
        luggage = luggage,
        handLuggage = handLuggage,
        isReturnable = isReturnable,
        isExchangable = isExchangable
    )
}

fun TicketModel.toEntity() = TicketEntity(
    id = id,
    badge = badge,
    price = price,
    providerName = providerName,
    company = company,
    departure = departure,
    arrival = arrival,
    hasTransfer = hasTransfer,
    hasVisaTransfer = hasVisaTransfer,
    luggage = luggage,
    handLuggage = handLuggage,
    isReturnable = isReturnable,
    isExchangable = isExchangable
)