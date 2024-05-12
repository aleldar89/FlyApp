package com.example.airtickets_data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.airtickets_data.database.AirticketsDao
import com.example.airtickets_data.entities.toEntity
import com.example.airtickets_data.models.OfferModel
import com.example.airtickets_data.models.TicketModel
import com.example.airtickets_data.models.TicketOfferModel
import com.example.airtickets_data.network.ApiService
import com.google.gson.internal.LinkedTreeMap
import javax.inject.Inject

private const val OFFERS_KEY = "offers"
private const val TICKETS_OFFERS_KEY = "tickets_offers"
private const val TICKETS_KEY = "tickets"

class AirticketsRepositoryImpl @Inject constructor(
    private val dao: AirticketsDao,
    private val apiService: ApiService,
) : AirticketsRepository {

    override val offersData: LiveData<List<OfferModel>> =
        dao.getOffers().map { list ->
            list.map {
                it.entityToModel()
            }
        }

    override val ticketsOffersData: LiveData<List<TicketOfferModel>> =
        dao.getTicketsOffers().map { list ->
            list.map {
                it.entityToModel()
            }
        }

    override val ticketsData: LiveData<List<TicketModel>> =
        dao.getTickets().map { list ->
            list.map {
                it.entityToModel()
            }
        }

    override suspend fun getOffersRemoteData() {
        dao.insertOffers(doNetworkRequest(OFFERS_KEY) {
            try {
                apiService.getOffers()
            } catch (e: Exception) {
                testOffersRequest()
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
                testTicketsOffersRequest()
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
                testTicketsRequest()
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

