package com.example.airtickets_data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.PriceModel

@Entity
data class OfferEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val town: String,
    val price: PriceModel
)