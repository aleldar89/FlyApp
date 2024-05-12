package com.example.airtickets_data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.airtickets_data.models.OfferModel
import com.example.airtickets_data.models.PriceModel
import com.example.airtickets_data.models.TicketOfferModel

@Entity
data class TicketOfferEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val timeRange: List<String>,
    val price: PriceModel
) {

    fun entityToModel() = TicketOfferModel(
        id = id, title = title, timeRange = timeRange, price = price
    )
}

fun TicketOfferModel.toEntity() = TicketOfferEntity(
    id = id, title = title, timeRange = timeRange, price = price
)