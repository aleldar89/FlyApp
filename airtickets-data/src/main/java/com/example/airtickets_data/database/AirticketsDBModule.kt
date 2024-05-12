package com.example.airtickets_data.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AirticketsDBModule {

    @Singleton
    @Provides
    fun provideAirticketsDB(
        @ApplicationContext
        context: Context
    ): AirticketsDB = Room.databaseBuilder(context, AirticketsDB::class.java, "airtickets.db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideAirticketsDao(airticketsDB: AirticketsDB): AirticketsDao = airticketsDB.airticketsDao()
}