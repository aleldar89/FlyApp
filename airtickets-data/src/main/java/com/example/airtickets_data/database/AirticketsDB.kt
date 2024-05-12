package com.example.airtickets_data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.airtickets_data.entities.OfferEntity
import com.example.airtickets_data.entities.TicketEntity
import com.example.airtickets_data.entities.TicketOfferEntity
import com.example.airtickets_data.models.TicketOfferModel

@Database(
    entities = [
        OfferEntity::class,
        TicketOfferEntity::class,
        TicketEntity::class
    ],
    version = 1,
    exportSchema = false
)

@TypeConverters(Converter::class)
abstract class AirticketsDB : RoomDatabase() {
    abstract fun airticketsDao(): AirticketsDao
}