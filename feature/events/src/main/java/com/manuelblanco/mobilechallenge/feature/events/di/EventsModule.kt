package com.manuelblanco.mobilechallenge.feature.events.di

import com.manuelblanco.mobilechallenge.core.domain.usecase.GetEventFromCacheUseCase
import com.manuelblanco.mobilechallenge.core.domain.usecase.GetEventsFilterUseCase
import com.manuelblanco.mobilechallenge.core.domain.usecase.GetEventsOfflineFirstUseCase
import com.manuelblanco.mobilechallenge.core.domain.usecase.GetEventsRemoteFirstUseCase
import com.manuelblanco.mobilechallenge.core.domain.usecase.SetEventsFilterUseCase
import com.manuelblanco.mobilechallenge.core.domain.usecase.GetEventFromCacheUseCaseImpl
import com.manuelblanco.mobilechallenge.core.domain.usecase.GetEventsFilterUseCaseImpl
import com.manuelblanco.mobilechallenge.core.domain.usecase.GetEventsOfflineFirstUseCaseImpl
import com.manuelblanco.mobilechallenge.core.domain.usecase.GetEventsRemoteFirstUseCaseImpl
import com.manuelblanco.mobilechallenge.core.domain.usecase.SetEventsFilterUseCaseImpl
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