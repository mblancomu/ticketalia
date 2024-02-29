package com.manuelblanco.mobilechallenge.core.testing.repository.usecase

import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.data.repository.EventsRepository
import com.manuelblanco.mobilechallenge.core.model.data.Cities
import com.manuelblanco.mobilechallenge.core.model.data.Event
import com.manuelblanco.mobilechallenge.core.model.data.EventsFilter
import com.manuelblanco.mobilechallenge.core.model.data.SortType
import com.manuelblanco.mobilechallenge.core.testing.data.eventDetail
import com.manuelblanco.mobilechallenge.core.testing.data.eventsFromCacheList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * Created by Manuel Blanco Murillo on 13/2/24.
 */
class FakeEventsRepository : EventsRepository {

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
    ): Flow<List<Event>> =
        flow {
            if (offset > eventsFromCacheList.size) {
                emit(emptyList())
            } else {
                emit(eventsFromCacheList)
            }
        }.catch { println("Some error") }

    override fun getEventFromCache(id: String): Flow<Result<Event>> = flow {
        emit(Result.Loading)
        if (id.isBlank()) {
            emit(Result.Error(exception = Throwable(message = "Wrong id")))
        } else {
            emit(eventDetail)
        }
    }.catch { Result.Error(exception = Throwable(message = "Some error")) }

    override fun getEventsFilter(): Flow<EventsFilter> = flow {
        emit(EventsFilter(sortType = SortType.NAME, city = Cities.ALL.city))
    }

    override suspend fun setEventsFilter(filters: EventsFilter) {}

}