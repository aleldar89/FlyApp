package com.example.airtickets_data.usecases

import com.example.airtickets_data.repository.AirticketsRepository
import javax.inject.Inject

class GetTicketsUseCase @Inject constructor(
    private val airticketsRepository: AirticketsRepository
) {
    fun getData() = airticketsRepository.ticketsData
}