package com.manuelblanco.mobilechallenge.core.testing.repository.usecase

import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.domain.repository.EventsRepository
import com.manuelblanco.mobilechallenge.core.domain.model.Event
import com.manuelblanco.mobilechallenge.core.domain.model.EventsFilter
import com.manuelblanco.mobilechallenge.core.testing.data.eventDetail
import com.manuelblanco.mobilechallenge.core.testing.data.eventsFromCacheList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * Created by Manuel Blanco Murillo on 13/2/24.
 */
class FakeEventsRepository :
    com.manuelblanco.mobilechallenge.core.domain.repository.EventsRepository {

    override suspend fun getEventsFromRemote(
        page: String,
        keyword: String,
        isRefreshing: Boolean
    ) {
    }

    override fun getEventsFromCache(
        page: String,
        limit: Int,
        offset: Int,
        keyword: String
    ): Flow<List<com.manuelblanco.mobilechallenge.core.domain.model.Event>> =
        flow {
            if (offset > eventsFromCacheList.size) {
                emit(emptyList())
            } else {
                emit(eventsFromCacheList)
            }
        }.catch { println("Some error") }

    override fun getEventFromCache(id: String): Flow<Result<com.manuelblanco.mobilechallenge.core.domain.model.Event>> = flow {
        emit(Result.Loading)
        if (id.isBlank()) {
            emit(Result.Error(exception = Throwable(message = "Wrong id")))
        } else {
            emit(eventDetail)
        }
    }.catch { Result.Error(exception = Throwable(message = "Some error")) }

    override fun getEventsFilter(): Flow<com.manuelblanco.mobilechallenge.core.domain.model.EventsFilter> = flow {
        emit(
            com.manuelblanco.mobilechallenge.core.domain.model.EventsFilter(
                sortType = com.manuelblanco.mobilechallenge.core.domain.model.SortType.NAME,
                city = com.manuelblanco.mobilechallenge.core.domain.model.Cities.ALL.city
            )
        )
    }

    override suspend fun setEventsFilter(filters: com.manuelblanco.mobilechallenge.core.domain.model.EventsFilter) {}

}