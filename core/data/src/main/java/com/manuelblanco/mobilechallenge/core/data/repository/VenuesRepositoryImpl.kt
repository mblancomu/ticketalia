package com.manuelblanco.mobilechallenge.core.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.common.result.asResult
import com.manuelblanco.mobilechallenge.core.data.mediator.VenuesRemoteMediator
import com.manuelblanco.mobilechallenge.core.database.dao.VenueDao
import com.manuelblanco.mobilechallenge.core.database.model.asExternalModel
import com.manuelblanco.mobilechallenge.core.model.data.Venue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class VenuesRepositoryImpl @Inject constructor(
    private val venueDao: VenueDao,
    private val remoteMediator: VenuesRemoteMediator
) : VenuesRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getVenues(): Flow<PagingData<Venue>> =
        Pager(
            config = PagingConfig(
                pageSize = 4,
                enablePlaceholders = true,
            ),
            pagingSourceFactory = {
                venueDao.getVenueEntities()
            },
            remoteMediator = remoteMediator
        ).flow.map { venue ->
            venue.map { it::asExternalModel.invoke() }
        }


    override fun getVenue(id: String): Flow<Result<Venue>> =
        venueDao.getVenueEntity(id).map { it.asExternalModel() }.asResult()

}