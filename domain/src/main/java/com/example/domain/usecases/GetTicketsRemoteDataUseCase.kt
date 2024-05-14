package com.example.domain.usecases

import com.example.domain.AirticketsRepository
import javax.inject.Inject

class GetTicketsRemoteDataUseCase @Inject constructor(
    private val airticketsRepository: AirticketsRepository
) {
    suspend fun getData() = airticketsRepository.getTicketsRemoteData()
}