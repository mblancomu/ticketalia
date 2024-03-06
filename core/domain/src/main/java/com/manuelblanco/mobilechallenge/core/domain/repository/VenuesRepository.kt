package com.manuelblanco.mobilechallenge.core.domain.repository

import androidx.paging.PagingData
import com.manuelblanco.mobilechallenge.core.domain.model.Venue
import com.manuelblanco.mobilechallenge.core.common.result.Result
import kotlinx.coroutines.flow.Flow

interface VenuesRepository {

    fun getVenues(): Flow<PagingData<Venue>>

    fun getVenue(id: String): Flow<Result<Venue>>
}