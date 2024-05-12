package com.example.airtickets_data.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface AirticketsRepositoryModule {

    @Singleton
    @Binds
    fun bindsAirticketsRepository(impl: AirticketsRepositoryImpl): AirticketsRepository
}