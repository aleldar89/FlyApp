package com.example.domain.usecases

import com.example.domain.AirticketsRepository
import javax.inject.Inject

class GetTicketsOffersRemoteDataUseCase @Inject constructor(
    private val airticketsRepository: AirticketsRepository
) {
    suspend fun getData() = airticketsRepository.getTicketsOffersRemoteData()
}