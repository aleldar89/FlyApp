package com.example.domain

import androidx.lifecycle.LiveData
import com.example.domain.models.OfferModel
import com.example.domain.models.TicketModel
import com.example.domain.models.TicketOfferModel

interface AirticketsRepository {

    val offersData: LiveData<List<OfferModel>>
    val ticketsOffersData: LiveData<List<TicketOfferModel>>
    val ticketsData: LiveData<List<TicketModel>>
    suspend fun getOffersRemoteData()
    suspend fun getTicketsOffersRemoteData()
    suspend fun getTicketsRemoteData()
}