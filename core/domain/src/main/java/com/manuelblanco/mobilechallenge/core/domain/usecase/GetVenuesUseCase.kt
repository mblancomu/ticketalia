package com.manuelblanco.mobilechallenge.core.domain.usecase

import androidx.paging.PagingData
import com.manuelblanco.mobilechallenge.core.domain.model.Venue
import kotlinx.coroutines.flow.Flow

interface GetVenuesUseCase {
    operator fun invoke(): Flow<PagingData<Venue>>
}