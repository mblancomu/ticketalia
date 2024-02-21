package com.manuelblanco.mobilechallenge.core.testing.repository.usecase

import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.data.repository.EventsRepository
import com.manuelblanco.mobilechallenge.core.model.data.Event
import com.manuelblanco.mobilechallenge.core.model.data.Page
import com.manuelblanco.mobilechallenge.core.testing.data.eventDetail
import com.manuelblanco.mobilechallenge.core.testing.data.eventsFromCacheList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * Created by Manuel Blanco Murillo on 13/2/24.
 */
class FakeEventsRepository : EventsRepository {
    override fun getEventsFromRemote(page: String, isRefreshing: Boolean): Flow<Result<Page>> = flow {
        emit(Result.Loading)
        if (page.isNotBlank()) {
            println("PAGE ES $page")
            if (page.toInt() != 1) {
                emit(Result.Error(exception = Throwable(message = "Parameter page do not match")))
            } else {
                emit(
                    Result.Success(
                        data = Page(
                            totalElements = 20,
                            totalPages = 3,
                            currentPage = 1
                        )
                    )
                )
            }
        } else {
            emit(Result.Error(exception = Throwable(message = "Parameter page empty")))
        }
    }.catch { Result.Error(exception = Throwable(message = "Some error")) }

    override fun getEventsFromCache(
        limit: Int,
        offset: Int
    ): Flow<List<Event>> = flow {
        if (offset > eventsFromCacheList.size) {
            emit(emptyList())
        } else {
            emit(eventsFromCacheList)
        }
    }.catch { println("Some error") }

    override fun getEventFromCache(id: String): Flow<Result<Event>> = flow {
        emit(Result.Loading)
        if (id.isBlank()){
            emit(Result.Error(exception = Throwable(message = "Wrong id")))
        } else {
            emit(eventDetail)
        }
    }.catch { Result.Error(exception = Throwable(message = "Some error")) }

    override fun getEventsFromRemoteByRadius(
        radius: String,
        unitRadius: String,
        page: String
    ): Flow<Result<List<Event>>> {
        TODO("Not yet implemented")
    }

    override fun getEventsFromRemoteByType(
        type: String,
        page: String
    ): Flow<Result<List<Event>>> {
        TODO("Not yet implemented")
    }

    override fun getEventsFromRemoteByCity(
        city: String,
        page: String
    ): Flow<Result<List<Event>>> {
        TODO("Not yet implemented")
    }
}