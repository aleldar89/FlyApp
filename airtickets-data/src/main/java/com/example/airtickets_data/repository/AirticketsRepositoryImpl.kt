package com.example.airtickets_data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.airtickets_data.database.AirticketsDao
import com.example.domain.models.OfferModel
import com.example.domain.models.TicketModel
import com.example.domain.models.TicketOfferModel
import com.example.airtickets_data.network.ApiService
import com.example.airtickets_data.toEntity
import com.example.airtickets_data.toModel
import com.example.domain.AirticketsRepository
import com.google.gson.internal.LinkedTreeMap
import javax.inject.Inject

private const val OFFERS_KEY = "offers"
private const val TICKETS_OFFERS_KEY = "tickets_offers"
private const val TICKETS_KEY = "tickets"

class AirticketsRepositoryImpl @Inject constructor(
    private val dao: AirticketsDao,
    private val apiService: ApiService
) : AirticketsRepository {

    override val offersData: LiveData<List<OfferModel>> =
        dao.getOffers().map { list ->
            list.map {
                it.toModel()
            }
        }

    override val ticketsOffersData: LiveData<List<TicketOfferModel>> =
        dao.getTicketsOffers().map { list ->
            list.map {
                it.toModel()
            }
        }

    override val ticketsData: LiveData<List<TicketModel>> =
        dao.getTickets().map { list ->
            list.map {
                it.toModel()
            }
        }

    override suspend fun getOffersRemoteData() {
        dao.insertOffers(doNetworkRequest(OFFERS_KEY) {
            try {
                apiService.getOffers()
            } catch (e: Exception) {
                testOffersRequest() // if server is not responding
            }
        }.map { model ->
            model.toEntity()
        })
    }


    override suspend fun getTicketsOffersRemoteData() {
        dao.insertTicketsOffers(doNetworkRequest(TICKETS_OFFERS_KEY) {
            try {
                apiService.getTicketsOffers()
            } catch (e: Exception) {
                testTicketsOffersRequest() // if server is not responding
            }
        }.map { model ->
            model.toEntity()
        })
    }

    override suspend fun getTicketsRemoteData() {
        dao.insertTickets(doNetworkRequest(TICKETS_KEY) {
            try {
                apiService.getTickets()
            } catch (e: Exception) {
                testTicketsRequest() // if server is not responding
            }
        }.map { model ->
            model.toEntity()
        })
    }

    private suspend fun <K, D> doNetworkRequest(
        key: K,
        request: suspend () -> LinkedTreeMap<K, List<D>>
    ): List<D> = request()[key] ?: throw Exception("Response body is null")
}

