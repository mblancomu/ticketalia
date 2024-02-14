package com.manuelblanco.mobilechallenge.core.testing.repository.usecase

import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.data.repository.VenuesRepository
import com.manuelblanco.mobilechallenge.core.model.data.Venue
import com.manuelblanco.mobilechallenge.core.testing.data.pagingVenues
import com.manuelblanco.mobilechallenge.core.testing.data.venueDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * Created by Manuel Blanco Murillo on 13/2/24.
 */
class FakeVenuesRepository : VenuesRepository {
    override fun getVenues(): Flow<androidx.paging.PagingData<Venue>> = flow {
        emit(pagingVenues)
    }.catch { println("Some error") }

    override fun getVenue(id: String): Flow<Result<Venue>> = flow {
        emit(Result.Loading)
        if (id.isBlank()) {
            emit(Result.Error(exception = Throwable(message = "Wrong id")))
        } else {
            emit(venueDetail)
        }
    }.catch { Result.Error(exception = Throwable(message = "Some error")) }

}