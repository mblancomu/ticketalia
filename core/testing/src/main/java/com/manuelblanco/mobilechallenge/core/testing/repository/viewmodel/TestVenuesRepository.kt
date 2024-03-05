package com.manuelblanco.mobilechallenge.core.testing.repository.viewmodel

import androidx.paging.PagingData
import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.common.result.asResult
import com.manuelblanco.mobilechallenge.core.domain.repository.VenuesRepository
import com.manuelblanco.mobilechallenge.core.domain.model.Venue
import com.manuelblanco.mobilechallenge.core.testing.utils.collectDataForTest
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map

/**
 * Created by Manuel Blanco Murillo on 14/2/24.
 */
class TestVenuesRepository :
    com.manuelblanco.mobilechallenge.core.domain.repository.VenuesRepository {

    private val venuesFlow: MutableSharedFlow<PagingData<com.manuelblanco.mobilechallenge.core.domain.model.Venue>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    fun senRemoteVenues(paging: PagingData<com.manuelblanco.mobilechallenge.core.domain.model.Venue>) {
        venuesFlow.tryEmit(paging)
    }

    override fun getVenues(): Flow<PagingData<com.manuelblanco.mobilechallenge.core.domain.model.Venue>> = venuesFlow

    override fun getVenue(id: String): Flow<Result<com.manuelblanco.mobilechallenge.core.domain.model.Venue>> =
        venuesFlow.map { venue ->
            venue.collectDataForTest().filter { it.id.isNotBlank() }.find { it.id == id }!!
        }.asResult()
}