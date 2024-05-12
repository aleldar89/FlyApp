package com.example.airtickets_data.network

import com.example.airtickets_data.models.OfferModel
import com.example.airtickets_data.models.TicketModel
import com.example.airtickets_data.models.TicketOfferModel
import com.google.gson.internal.LinkedTreeMap
import retrofit2.http.GET

interface ApiService {

    @GET("00727197-24ae-48a0-bcb3-63eb35d7a9de")
    suspend fun getOffers(): LinkedTreeMap<String, List<OfferModel>>

    @GET("3424132d-a51a-4613-b6c9-42b2d214f35f")
    suspend fun getTicketsOffers(): LinkedTreeMap<String, List<TicketOfferModel>>

    @GET("2dbc0999-86bf-4c08-8671-bac4b7a5f7eb")
    suspend fun getTickets(): LinkedTreeMap<String, List<TicketModel>>
}