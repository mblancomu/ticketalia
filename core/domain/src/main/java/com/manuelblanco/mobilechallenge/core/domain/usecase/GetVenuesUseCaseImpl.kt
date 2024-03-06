package com.manuelblanco.mobilechallenge.core.domain.usecase

import androidx.paging.PagingData
import com.manuelblanco.mobilechallenge.core.domain.repository.VenuesRepository
import com.manuelblanco.mobilechallenge.core.domain.usecase.GetVenuesUseCase
import com.manuelblanco.mobilechallenge.core.domain.model.Venue
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