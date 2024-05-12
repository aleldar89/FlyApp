package com.example.airtickets_data.repository

import androidx.lifecycle.LiveData
import com.example.airtickets_data.models.OfferModel
import com.example.airtickets_data.models.TicketModel
import com.example.airtickets_data.models.TicketOfferModel

interface AirticketsRepository {

    val offersData: LiveData<List<OfferModel>>
    val ticketsOffersData: LiveData<List<TicketOfferModel>>
    val ticketsData: LiveData<List<TicketModel>>
    suspend fun getOffersRemoteData()
    suspend fun getTicketsOffersRemoteData()
    suspend fun getTicketsRemoteData()
}