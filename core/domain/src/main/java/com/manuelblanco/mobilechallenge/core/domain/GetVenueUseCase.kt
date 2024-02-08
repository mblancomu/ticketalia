package com.manuelblanco.mobilechallenge.core.domain

import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.model.data.Venue
import kotlinx.coroutines.flow.Flow

interface GetVenueUseCase {
    operator fun invoke(id: String): Flow<Result<Venue>>
}