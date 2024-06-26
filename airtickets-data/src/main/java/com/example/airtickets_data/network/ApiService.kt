package com.example.airtickets_data.network

import com.example.airtickets_data.dto.OfferDTO
import com.example.airtickets_data.dto.TicketDTO
import com.example.airtickets_data.dto.TicketOfferDTO
import com.google.gson.internal.LinkedTreeMap
import retrofit2.http.GET

interface ApiService {

    @GET("ad9a46ba-276c-4a81-88a6-c068e51cce3a")
    suspend fun getOffers(): LinkedTreeMap<String, List<OfferDTO>>

    @GET("38b5205d-1a3d-4c2f-9d77-2f9d1ef01a4a")
    suspend fun getTicketsOffers(): LinkedTreeMap<String, List<TicketOfferDTO>>

    @GET("c0464573-5a13-45c9-89f8-717436748b69")
    suspend fun getTickets(): LinkedTreeMap<String, List<TicketDTO>>
}