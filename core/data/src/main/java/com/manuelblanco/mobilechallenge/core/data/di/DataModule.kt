package com.manuelblanco.mobilechallenge.core.data.di

import com.manuelblanco.mobilechallenge.core.domain.repository.EventsRepository
import com.manuelblanco.mobilechallenge.core.data.repository.EventsRepositoryImpl
import com.manuelblanco.mobilechallenge.core.domain.repository.VenuesRepository
import com.manuelblanco.mobilechallenge.core.data.repository.VenuesRepositoryImpl
import com.manuelblanco.mobilechallenge.core.data.util.ConnectivityManagerNetworkMonitor
import com.manuelblanco.mobilechallenge.core.data.util.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Manuel Blanco Murillo on 26/6/23.
 */

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindEventsOfflineFirstRepository(
        eventsOfflineFirstRepository: EventsRepositoryImpl
    ): EventsRepository

    @Binds
    fun bindsVenuesRepository(
        venuesRepository: VenuesRepositoryImpl,
    ): VenuesRepository

    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor
}