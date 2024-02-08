package com.manuelblanco.mobilechallenge.feature.events.di

import com.manuelblanco.mobilechallenge.core.domain.GetEventFromCacheUseCase
import com.manuelblanco.mobilechallenge.core.domain.GetEventsFromCacheUseCase
import com.manuelblanco.mobilechallenge.core.domain.GetEventsFromRemoteUseCase
import com.manuelblanco.mobilechallenge.feature.events.usecases.GetEventFromCacheUseCaseImpl
import com.manuelblanco.mobilechallenge.feature.events.usecases.GetEventsFromCacheUseCaseImpl
import com.manuelblanco.mobilechallenge.feature.events.usecases.GetEventsFromRemoteUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Manuel Blanco Murillo on 31/1/24.
 */

@Module
@InstallIn(SingletonComponent::class)
interface EventsModule {

    @Binds
    fun bindsGetEventsFromRemoteUseCase(
        getEventsFromRemoteUseCase: GetEventsFromRemoteUseCaseImpl,
    ): GetEventsFromRemoteUseCase

    @Binds
    fun bindsGetEventsFromCacheUseCase(
        getEventsFromCacheUseCase: GetEventsFromCacheUseCaseImpl,
    ): GetEventsFromCacheUseCase

    @Binds
    fun bindsGetEventFromCacheUseCase(
        getEventFromCacheUseCase: GetEventFromCacheUseCaseImpl,
    ): GetEventFromCacheUseCase

}