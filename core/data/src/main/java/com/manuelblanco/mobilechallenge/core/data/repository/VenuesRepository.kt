package com.manuelblanco.mobilechallenge.core.data.repository

import androidx.paging.PagingData
import com.manuelblanco.mobilechallenge.core.model.data.Venue
import com.manuelblanco.mobilechallenge.core.common.result.Result
import kotlinx.coroutines.flow.Flow

interface VenuesRepository {

    fun getVenues(): Flow<PagingData<Venue>>

    fun getVenue(id: String): Flow<Result<Venue>>
}