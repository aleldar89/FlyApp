package com.example.airtickets_data.usecases

import com.example.airtickets_data.repository.AirticketsRepository
import javax.inject.Inject

class GetTicketsRemoteDataUseCase @Inject constructor(
    private val airticketsRepository: AirticketsRepository
) {
    suspend fun getData() = airticketsRepository.getTicketsRemoteData()
}