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

    override val offersData: LiveData<List<OfferModel>> = dao.getOffers().mapList { it.toModel() }

    override val ticketsOffersData: LiveData<List<TicketOfferModel>> =
        dao.getTicketsOffers().mapList { it.toModel() }

    override val ticketsData: LiveData<List<TicketModel>> =
        dao.getTickets().mapList { it.toModel() }

    override suspend fun getOffersRemoteData() = getDataAndInsert(
        insert = { dao.insertOffers(it) },
        key = OFFERS_KEY,
        request = { apiService.getOffers() },
        localTestRequest = { testOffersRequest() },
        mapDtoToEntity = { it.toEntity() }
    )

    override suspend fun getTicketsOffersRemoteData() = getDataAndInsert(
        insert = { dao.insertTicketsOffers(it) },
        key = TICKETS_OFFERS_KEY,
        request = { apiService.getTicketsOffers() },
        localTestRequest = { testTicketsOffersRequest() },
        mapDtoToEntity = { it.toEntity() }
    )

    override suspend fun getTicketsRemoteData() = getDataAndInsert(
        insert = { dao.insertTickets(it) },
        key = TICKETS_KEY,
        request = { apiService.getTickets() },
        localTestRequest = { testTicketsRequest() },
        mapDtoToEntity = { it.toEntity() }
    )

    private suspend fun <K, D, E> getDataAndInsert(
        key: K,
        request: suspend () -> LinkedTreeMap<K, List<D>>,
        localTestRequest: suspend () -> LinkedTreeMap<K, List<D>>,
        mapDtoToEntity: (D) -> E,
        insert: suspend (List<E>) -> Unit
    ) = insert(
        try {
            doNetworkRequest(key, request)
        } catch (e: Exception) {
            doNetworkRequest(key, localTestRequest)
        }.map(mapDtoToEntity)
    )

    private suspend fun <K, D> doNetworkRequest(
        key: K,
        request: suspend () -> LinkedTreeMap<K, List<D>>
    ): List<D> = request()[key] ?: throw Exception("Response body is null")

    private fun <E, M> LiveData<List<E>>.mapList(entityToModel: (E) -> M): LiveData<List<M>> =
        this.map { list -> list.map(entityToModel) }
}