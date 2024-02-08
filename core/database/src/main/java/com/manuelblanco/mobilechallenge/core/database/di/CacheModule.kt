package com.manuelblanco.mobilechallenge.core.database.di

import com.manuelblanco.mobilechallenge.core.database.Cache
import com.manuelblanco.mobilechallenge.core.database.TicketsCache
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Manuel Blanco Murillo on 1/2/24.
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

    @Binds
    abstract fun bindCache(cache: TicketsCache): Cache
}