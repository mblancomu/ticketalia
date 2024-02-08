package com.manuelblanco.mobilechallenge.feature.venues.usecases

import androidx.paging.PagingData
import com.manuelblanco.mobilechallenge.core.data.repository.VenuesRepository
import com.manuelblanco.mobilechallenge.core.domain.GetVenuesUseCase
import com.manuelblanco.mobilechallenge.core.model.data.Venue
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Manuel Blanco Murillo on 31/1/24.
 */
class GetVenuesUseCaseImpl @Inject constructor(
    private val venuesRepository: VenuesRepository
) : GetVenuesUseCase {
    override fun invoke(): Flow<PagingData<Venue>> =
        venuesRepository.getVenues()
}