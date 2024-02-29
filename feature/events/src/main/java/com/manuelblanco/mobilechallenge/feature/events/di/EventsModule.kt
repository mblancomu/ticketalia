package com.manuelblanco.mobilechallenge.feature.events.di

import com.manuelblanco.mobilechallenge.core.domain.GetEventFromCacheUseCase
import com.manuelblanco.mobilechallenge.core.domain.GetEventsFilterUseCase
import com.manuelblanco.mobilechallenge.core.domain.GetEventsOfflineFirstUseCase
import com.manuelblanco.mobilechallenge.core.domain.GetEventsRemoteFirstUseCase
import com.manuelblanco.mobilechallenge.core.domain.SetEventsFilterUseCase
import com.manuelblanco.mobilechallenge.feature.events.usecases.GetEventFromCacheUseCaseImpl
import com.manuelblanco.mobilechallenge.feature.events.usecases.GetEventsFilterUseCaseImpl
import com.manuelblanco.mobilechallenge.feature.events.usecases.GetEventsOfflineFirstUseCaseImpl
import com.manuelblanco.mobilechallenge.feature.events.usecases.GetEventsRemoteFirstUseCaseImpl
import com.manuelblanco.mobilechallenge.feature.events.usecases.SetEventsFilterUseCaseImpl
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
    fun bindsGetEventFromCacheUseCase(
        getEventFromCacheUseCase: GetEventFromCacheUseCaseImpl,
    ): GetEventFromCacheUseCase

    @Binds
    fun bindsGetEventsOfflineFirstUseCase(
        getEventsOfflineFirstUseCase: GetEventsOfflineFirstUseCaseImpl,
    ): GetEventsOfflineFirstUseCase

    @Binds
    fun bindsGetEventsRemoteFirstUseCase(
        getEventsRemoteFirstUseCase: GetEventsRemoteFirstUseCaseImpl,
    ): GetEventsRemoteFirstUseCase

    @Binds
    fun bindGetEventsFilterUseCase(
        getEventsFilterUseCase: GetEventsFilterUseCaseImpl,
    ): GetEventsFilterUseCase

    @Binds
    fun bindSetEventsFilterUseCase(
        stEventsFilterUseCase: SetEventsFilterUseCaseImpl,
    ): SetEventsFilterUseCase

}