package com.manuelblanco.mobilechallenge.core.data.di

import com.manuelblanco.mobilechallenge.core.data.mediator.VenuesRemoteMediator
import com.manuelblanco.mobilechallenge.core.database.dao.RemoteKeysDao
import com.manuelblanco.mobilechallenge.core.database.dao.VenueDao
import com.manuelblanco.mobilechallenge.core.database.utils.TransactionProvider
import com.manuelblanco.mobilechallenge.core.network.retrofit.RetrofitNetworkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Manuel Blanco Murillo on 28/6/23.
 */

@Module
@InstallIn(SingletonComponent::class)
object MediatorModule {
    @Provides
    fun providesVenuesRemoteMediator(
        apiService: RetrofitNetworkApi,
        venueDao: VenueDao,
        remoteKeysDao: RemoteKeysDao,
        transactionProvider: TransactionProvider
    ) = VenuesRemoteMediator(
        apiService,
        venueDao,
        remoteKeysDao,
        transactionProvider
    )
}