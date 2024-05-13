package com.example.airtickets_data.repository

import com.example.airtickets_data.models.FlightInfo
import com.example.airtickets_data.models.HandLuggage
import com.example.airtickets_data.models.Luggage
import com.example.airtickets_data.models.OfferModel
import com.example.airtickets_data.models.PriceModel
import com.example.airtickets_data.models.TicketModel
import com.example.airtickets_data.models.TicketOfferModel
import com.google.gson.internal.LinkedTreeMap

fun testOffersRequest(): LinkedTreeMap<String, List<OfferModel>> {
    val list = listOf(
        OfferModel(
            id = 1,
            title = "Die Antwoord",
            town = "Будапешт",
            price = PriceModel(5000)
        ),
        OfferModel(
            id = 2,
            title = "Socrat&Lera",
            town = "Санкт-Петербург",
            price = PriceModel(1999)
        ),
        OfferModel(
            id = 3,
            title = "Лампабикт",
            town = "Москва",
            price = PriceModel(2390)
        )
    )

    val response = LinkedTreeMap<String, List<OfferModel>>()
    response["offers"] = list

    return response
}

fun testTicketsOffersRequest(): LinkedTreeMap<String, List<TicketOfferModel>> {
    val list = listOf(
        TicketOfferModel(
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
            price = PriceModel(value = 3999)
        ),
        TicketOfferModel(
            id = 10,
            title = "Победа",
            timeRange = listOf("09:10", "21:00"),
            price = PriceModel(value = 4999)
        ),
        TicketOfferModel(
            id = 30,
            title = "NordStar",
            timeRange = listOf("07:00"),
            price = PriceModel(value = 2390)
        )
    )

    val response = LinkedTreeMap<String, List<TicketOfferModel>>()
    response["tickets_offers"] = list

    return response
}

fun testTicketsRequest(): LinkedTreeMap<String, List<TicketModel>> {
    // Создание списка билетов
    val list = listOf(
        TicketModel(
            id = 100,
            badge = "Самый удобный",
            price = PriceModel(1999),
            providerName = "На сайте Купибилет",
            company = "Якутия",
            departure = FlightInfo("Москва", "2024-02-23T03:15:00", "VKO"),
            arrival = FlightInfo("Сочи", "2024-02-23T07:10:00", "AER"),
            hasTransfer = false,
            hasVisaTransfer = false,
            luggage = Luggage(false, PriceModel(1082)),
            handLuggage = HandLuggage(true, "55x20x40"),
            isReturnable = false,
            isExchangable = true
        ),
        TicketModel(
            id = 101,
            price = PriceModel(9999),
            providerName = "На сайте Победа",
            company = "Победа",
            departure = FlightInfo("Москва", "2024-02-23T04:00:00", "VKO"),
            arrival = FlightInfo("Сочи", "2024-02-23T08:30:00", "AER"),
            hasTransfer = false,
            hasVisaTransfer = false,
            luggage = Luggage(false, PriceModel(1602)),
            handLuggage = HandLuggage(true, "36x30x27"),
            isReturnable = false,
            isExchangable = true
        ),
        TicketModel(
            id = 102,
            price = PriceModel(8500),
            providerName = "На сайте Turkish Airlines",
            company = "Турецкие авиалинии",
            departure = FlightInfo("Москва", "2024-02-23T15:00:00", "VKO"),
            arrival = FlightInfo("Сочи", "2024-02-23T18:40:00", "AER"),
            hasTransfer = false,
            hasVisaTransfer = false,
            luggage = Luggage(true),
            handLuggage = HandLuggage(true, "55x20x40"),
            isReturnable = false,
            isExchangable = false
        ),
        TicketModel(
            id = 103,
            badge = "Рекомендуемый",
            price = PriceModel(8086),
            providerName = "На сайте Уральские авиалинии",
            company = "Уральские авиалинии",
            departure = FlightInfo("Москва", "2024-02-23T11:30:00", "VKO"),
            arrival = FlightInfo("Сочи", "2024-02-23T15:00:00", "AER"),
            hasTransfer = false,
            hasVisaTransfer = false,
            luggage = Luggage(false, PriceModel(1570)),
            handLuggage = HandLuggage(true, "55x20x40"),
            isReturnable = true,
            isExchangable = true
        ),
        TicketModel(
            id = 104,
            price = PriceModel(11560),
            providerName = "На сайте Aeroflot",
            company = "Аэрофлот",
            departure = FlightInfo("Москва", "2024-02-23T11:50:00", "VKO"),
            arrival = FlightInfo("Сочи", "2024-02-23T15:20:00", "AER"),
            hasTransfer = true,
            hasVisaTransfer = false,
            luggage = Luggage(false, PriceModel(999)),
            handLuggage = HandLuggage(true, "55x20x40"),
            isReturnable = false,
            isExchangable = true
        ),
        TicketModel(
            id = 105,
            price = PriceModel(15600),
            providerName = "На сайте Aeroflot",
            company = "Аэрофлот",
            departure = FlightInfo("Москва", "2024-02-23T13:50:00", "VKO"),
            arrival = FlightInfo("Сочи", "2024-02-23T17:20:00", "AER"),
            hasTransfer = true,
            hasVisaTransfer = true,
            luggage = Luggage(false, PriceModel(1999)),
            handLuggage = HandLuggage(true, "55x20x40"),
            isReturnable = true,
            isExchangable = true
        ),
        TicketModel(
            id = 106,
            price = PriceModel(9500),
            providerName = "На сайте Aeroflot",
            company = "Аэрофлот",
            departure = FlightInfo("Москва", "2024-02-23T21:00:00", "VKO"),
            arrival = FlightInfo("Сочи", "2024-02-24T00:20:00", "AER"),
            hasTransfer = false,
            hasVisaTransfer = false,
            luggage = Luggage(false, PriceModel(5999)),
            handLuggage = HandLuggage(false),
            isReturnable = false,
            isExchangable = false
        )
    )

    val response = LinkedTreeMap<String, List<TicketModel>>()
    response["tickets"] = list

    return response
}