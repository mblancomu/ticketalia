package com.manuelblanco.mobilechallenge.feature.venues.usecases

import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.data.repository.VenuesRepository
import com.manuelblanco.mobilechallenge.core.domain.GetVenueUseCase
import com.manuelblanco.mobilechallenge.core.model.data.Venue
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Manuel Blanco Murillo on 31/1/24.
 */
class GetVenueUseCaseImpl @Inject constructor(
    private val venuesRepository: VenuesRepository
) : GetVenueUseCase {

    override fun invoke(id: String): Flow<Result<Venue>> =
        venuesRepository.getVenue(id)
}