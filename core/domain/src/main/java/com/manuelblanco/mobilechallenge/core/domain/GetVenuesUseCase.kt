package com.manuelblanco.mobilechallenge.core.domain

import androidx.paging.PagingData
import com.manuelblanco.mobilechallenge.core.model.data.Venue
import kotlinx.coroutines.flow.Flow

interface GetVenuesUseCase {
    operator fun invoke(): Flow<PagingData<Venue>>
}