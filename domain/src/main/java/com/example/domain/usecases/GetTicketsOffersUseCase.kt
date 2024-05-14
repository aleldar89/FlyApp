package com.example.domain.usecases

import com.example.domain.AirticketsRepository
import javax.inject.Inject

class GetTicketsOffersUseCase @Inject constructor(
    private val airticketsRepository: AirticketsRepository
) {
    fun getData() = airticketsRepository.ticketsOffersData
}