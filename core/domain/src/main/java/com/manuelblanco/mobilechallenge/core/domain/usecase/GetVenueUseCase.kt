package com.manuelblanco.mobilechallenge.core.domain.usecase

import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.domain.model.Venue
import kotlinx.coroutines.flow.Flow

interface GetVenueUseCase {
    operator fun invoke(id: String): Flow<Result<Venue>>
}