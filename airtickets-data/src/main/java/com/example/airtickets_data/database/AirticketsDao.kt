package com.example.airtickets_data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.airtickets_data.entities.OfferEntity
import com.example.airtickets_data.entities.TicketEntity
import com.example.airtickets_data.entities.TicketOfferEntity

@Dao
interface AirticketsDao {

    @Query("SELECT * FROM OfferEntity ORDER BY id DESC")
    fun getOffers(): LiveData<List<OfferEntity>>

    @Query("SELECT * FROM TicketOfferEntity ORDER BY id DESC")
    fun getTicketsOffers(): LiveData<List<TicketOfferEntity>>

    @Query("SELECT * FROM TicketEntity ORDER BY id DESC")
    fun getTickets(): LiveData<List<TicketEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOffers(list: List<OfferEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTicketsOffers(list: List<TicketOfferEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTickets(list: List<TicketEntity>)
}