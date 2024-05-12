package com.example.airtickets_data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.airtickets_data.models.OfferModel
import com.example.airtickets_data.models.PriceModel

@Entity
data class OfferEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val town: String,
    val price: PriceModel
) {

    fun entityToModel() = OfferModel(
        id = id, title = title, town = town, price = price
    )
}

fun OfferModel.toEntity() = OfferEntity(
    id = id, title = title, town = town, price = price
)