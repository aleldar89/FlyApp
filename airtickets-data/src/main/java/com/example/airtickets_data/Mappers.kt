package com.example.airtickets_data

import com.example.airtickets_data.dto.OfferDTO
import com.example.airtickets_data.dto.TicketDTO
import com.example.airtickets_data.dto.TicketOfferDTO
import com.example.airtickets_data.entities.OfferEntity
import com.example.airtickets_data.entities.TicketEntity
import com.example.airtickets_data.entities.TicketOfferEntity
import com.example.domain.models.FlightInfoModel
import com.example.domain.models.HandLuggageModel
import com.example.domain.models.LuggageModel
import com.example.domain.models.OfferModel
import com.example.domain.models.PriceModel
import com.example.domain.models.TicketModel
import com.example.domain.models.TicketOfferModel

fun OfferDTO.toEntity() = OfferEntity(
    id = id,
    title = title,
    town = town,
    price = PriceModel(price.value)
)

fun TicketOfferDTO.toEntity() = TicketOfferEntity(
    id = id,
    title = title,
    timeRange = timeRange,
    price = PriceModel(price.value)
)

fun TicketDTO.toEntity() = TicketEntity(
    id = id,
    badge = badge,
    price = PriceModel(price.value),
    providerName = providerName,
    company = company,
    departure = FlightInfoModel(departure.town, departure.date, departure.airport),
    arrival = FlightInfoModel(arrival.town, arrival.date, arrival.airport),
    hasTransfer = hasTransfer,
    hasVisaTransfer = hasVisaTransfer,
    luggage = LuggageModel(
        luggage.hasLuggage,
        if (luggage.price == null) null else PriceModel(luggage.price.value)
    ),
    handLuggage = HandLuggageModel(handLuggage.hasHandLuggage, handLuggage.size),
    isReturnable = isReturnable,
    isExchangable = isExchangable
)

fun OfferEntity.toModel() = OfferModel(
    id = id, title = title, town = town, price = price
)

fun TicketOfferEntity.toModel() = TicketOfferModel(
    id = id, title = title, timeRange = timeRange, price = price
)

fun TicketEntity.toModel() = TicketModel(
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