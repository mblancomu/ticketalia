package com.manuelblanco.mobilechallenge.core.database.di

import com.manuelblanco.mobilechallenge.core.database.ChallengeDatabase
import com.manuelblanco.mobilechallenge.core.database.dao.EventDao
import com.manuelblanco.mobilechallenge.core.database.dao.RemoteKeysDao
import com.manuelblanco.mobilechallenge.core.database.dao.VenueDao
import com.manuelblanco.mobilechallenge.core.database.utils.TransactionProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Manuel Blanco Murillo on 26/6/23.
 */

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun providesEventDao(
        database: ChallengeDatabase,
    ): EventDao = database.eventDao()

    @Provides
    fun providesVenueDao(
        database: ChallengeDatabase,
    ): VenueDao = database.venueDao()

    @Provides
    fun providesRemoteKeysDao(
        database: ChallengeDatabase,
    ): RemoteKeysDao = database.remoteKeyDao()

    @Provides
    fun providesTransactionProvider(
        database: ChallengeDatabase,
    ) = TransactionProvider(database)
}