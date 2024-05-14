package com.example.airtickets_data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.PriceModel

@Entity
data class TicketOfferEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val timeRange: List<String>,
    val price: PriceModel
)