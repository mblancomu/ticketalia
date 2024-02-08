package com.manuelblanco.mobilechallenge.feature.venues.di

import com.manuelblanco.mobilechallenge.core.domain.GetVenueUseCase
import com.manuelblanco.mobilechallenge.core.domain.GetVenuesUseCase
import com.manuelblanco.mobilechallenge.feature.venues.usecases.GetVenueUseCaseImpl
import com.manuelblanco.mobilechallenge.feature.venues.usecases.GetVenuesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Manuel Blanco Murillo on 31/1/24.
 */

@Module
@InstallIn(SingletonComponent::class)
interface VenuesModule {

    @Binds
    fun bindsGetVenuesUseCase(
        getVenuesUseCase: GetVenuesUseCaseImpl,
    ): GetVenuesUseCase

    @Binds
    fun bindsGetVenueUseCase(
        getVenueUseCase: GetVenueUseCaseImpl,
    ): GetVenueUseCase

}