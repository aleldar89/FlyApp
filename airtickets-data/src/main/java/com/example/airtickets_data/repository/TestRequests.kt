package com.example.airtickets_data.repository

import com.example.airtickets_data.dto.FlightInfoDTO
import com.example.airtickets_data.dto.HandLuggageDTO
import com.example.airtickets_data.dto.LuggageDTO
import com.example.airtickets_data.dto.OfferDTO
import com.example.airtickets_data.dto.PriceDTO
import com.example.airtickets_data.dto.TicketDTO
import com.example.airtickets_data.dto.TicketOfferDTO
import com.google.gson.internal.LinkedTreeMap

fun testOffersRequest(): LinkedTreeMap<String, List<OfferDTO>> {
    val list = listOf(
        OfferDTO(
            id = 1,
            title = "Die Antwoord",
            town = "Будапешт",
            price = PriceDTO(5000)
        ),
        OfferDTO(
            id = 2,
            title = "Socrat&Lera",
            town = "Санкт-Петербург",
            price = PriceDTO(1999)
        ),
        OfferDTO(
            id = 3,
            title = "Лампабикт",
            town = "Москва",
            price = PriceDTO(2390)
        )
    )

    val response = LinkedTreeMap<String, List<OfferDTO>>()
    response["offers"] = list

    return response
}

fun testTicketsOffersRequest(): LinkedTreeMap<String, List<TicketOfferDTO>> {
    val list = listOf(
        TicketOfferDTO(
            id = 1,
            title = "Уральские авиалинии",
            timeRange = listOf(
                "07:00",
                "09:10",
                "10:00",
                "11:30",
                "14:15",
                "19:10",
                "21:00",
                "23:30"
            ),
            price = PriceDTO(value = 3999)
        ),
        TicketOfferDTO(
            id = 10,
            title = "Победа",
            timeRange = listOf("09:10", "21:00"),
            price = PriceDTO(value = 4999)
        ),
        TicketOfferDTO(
            id = 30,
            title = "NordStar",
            timeRange = listOf("07:00"),
            price = PriceDTO(value = 2390)
        )
    )

    val response = LinkedTreeMap<String, List<TicketOfferDTO>>()
    response["tickets_offers"] = list

    return response
}

fun testTicketsRequest(): LinkedTreeMap<String, List<TicketDTO>> {
    // Создание списка билетов
    val list = listOf(
        TicketDTO(
            id = 100,
            badge = "Самый удобный",
            price = PriceDTO(1999),
            providerName = "На сайте Купибилет",
            company = "Якутия",
            departure = FlightInfoDTO("Москва", "2024-02-23T03:15:00", "VKO"),
            arrival = FlightInfoDTO("Сочи", "2024-02-23T07:10:00", "AER"),
            hasTransfer = false,
            hasVisaTransfer = false,
            luggage = LuggageDTO(false, PriceDTO(1082)),
            handLuggage = HandLuggageDTO(true, "55x20x40"),
            isReturnable = false,
            isExchangable = true
        ),
        TicketDTO(
            id = 101,
            price = PriceDTO(9999),
            providerName = "На сайте Победа",
            company = "Победа",
            departure = FlightInfoDTO("Москва", "2024-02-23T04:00:00", "VKO"),
            arrival = FlightInfoDTO("Сочи", "2024-02-23T08:30:00", "AER"),
            hasTransfer = false,
            hasVisaTransfer = false,
            luggage = LuggageDTO(false, PriceDTO(1602)),
            handLuggage = HandLuggageDTO(true, "36x30x27"),
            isReturnable = false,
            isExchangable = true
        ),
        TicketDTO(
            id = 102,
            price = PriceDTO(8500),
            providerName = "На сайте Turkish Airlines",
            company = "Турецкие авиалинии",
            departure = FlightInfoDTO("Москва", "2024-02-23T15:00:00", "VKO"),
            arrival = FlightInfoDTO("Сочи", "2024-02-23T18:40:00", "AER"),
            hasTransfer = false,
            hasVisaTransfer = false,
            luggage = LuggageDTO(true),
            handLuggage = HandLuggageDTO(true, "55x20x40"),
            isReturnable = false,
            isExchangable = false
        ),
        TicketDTO(
            id = 103,
            badge = "Рекомендуемый",
            price = PriceDTO(8086),
            providerName = "На сайте Уральские авиалинии",
            company = "Уральские авиалинии",
            departure = FlightInfoDTO("Москва", "2024-02-23T11:30:00", "VKO"),
            arrival = FlightInfoDTO("Сочи", "2024-02-23T15:00:00", "AER"),
            hasTransfer = false,
            hasVisaTransfer = false,
            luggage = LuggageDTO(false, PriceDTO(1570)),
            handLuggage = HandLuggageDTO(true, "55x20x40"),
            isReturnable = true,
            isExchangable = true
        ),
        TicketDTO(
            id = 104,
            price = PriceDTO(11560),
            providerName = "На сайте Aeroflot",
            company = "Аэрофлот",
            departure = FlightInfoDTO("Москва", "2024-02-23T11:50:00", "VKO"),
            arrival = FlightInfoDTO("Сочи", "2024-02-23T15:20:00", "AER"),
            hasTransfer = true,
            hasVisaTransfer = false,
            luggage = LuggageDTO(false, PriceDTO(999)),
            handLuggage = HandLuggageDTO(true, "55x20x40"),
            isReturnable = false,
            isExchangable = true
        ),
        TicketDTO(
            id = 105,
            price = PriceDTO(15600),
            providerName = "На сайте Aeroflot",
            company = "Аэрофлот",
            departure = FlightInfoDTO("Москва", "2024-02-23T13:50:00", "VKO"),
            arrival = FlightInfoDTO("Сочи", "2024-02-23T17:20:00", "AER"),
            hasTransfer = true,
            hasVisaTransfer = true,
            luggage = LuggageDTO(false, PriceDTO(1999)),
            handLuggage = HandLuggageDTO(true, "55x20x40"),
            isReturnable = true,
            isExchangable = true
        ),
        TicketDTO(
            id = 106,
            price = PriceDTO(9500),
            providerName = "На сайте Aeroflot",
            company = "Аэрофлот",
            departure = FlightInfoDTO("Москва", "2024-02-23T21:00:00", "VKO"),
            arrival = FlightInfoDTO("Сочи", "2024-02-24T00:20:00", "AER"),
            hasTransfer = false,
            hasVisaTransfer = false,
            luggage = LuggageDTO(false, PriceDTO(5999)),
            handLuggage = HandLuggageDTO(false),
            isReturnable = false,
            isExchangable = false
        )
    )

    val response = LinkedTreeMap<String, List<TicketDTO>>()
    response["tickets"] = list

    return response
}