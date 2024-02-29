package com.manuelblanco.mobilechallenge.core.data.repository

import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.common.result.asResult
import com.manuelblanco.mobilechallenge.core.data.model.asEventEntities
import com.manuelblanco.mobilechallenge.core.database.TicketsCache
import com.manuelblanco.mobilechallenge.core.database.model.asExternalModel
import com.manuelblanco.mobilechallenge.core.datastore.TicketsDataStore
import com.manuelblanco.mobilechallenge.core.datastore.TicketsPreferences
import com.manuelblanco.mobilechallenge.core.model.data.Cities
import com.manuelblanco.mobilechallenge.core.model.data.Event
import com.manuelblanco.mobilechallenge.core.model.data.EventsFilter
import com.manuelblanco.mobilechallenge.core.model.data.SortType
import com.manuelblanco.mobilechallenge.core.network.retrofit.TicketsRemote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class EventsRepositoryImpl @Inject constructor(
    private val cache: TicketsCache,
    private val api: TicketsRemote,
    private val preferences: TicketsPreferences
) : EventsRepository {

    private val PAGE_SIZE = "20"
    private val SORT_BY = "name,desc"
    private val COUNTRY_CODE = "ES"

    override suspend fun getEventsFromRemote(
        page: String,
        keyword: String,
        isRefreshing: Boolean
    ) {
        api.getEvents(
            page = page,
            size = PAGE_SIZE,
            sort = SORT_BY,
            countryCode = COUNTRY_CODE,
            keyword = keyword
        ).also {
            if (isRefreshing) {
                cache.invalidateCache()
            }
            cache.saveEventsInCache(events = it.asEventEntities())
        }
    }

    override fun getEventsFromCache(
        page: String,
        limit: Int,
        offset: Int,
        keyword: String
    ): Flow<List<Event>> =
        cache.getEventsFromCache(limit, offset)
            .map { event -> event.map { it::asExternalModel.invoke() } }.onEach {
                if (it.isEmpty()) {
                    getEventsFromRemote(
                        page = page, isRefreshing = false,
                        keyword = keyword
                    )
                }
            }

    override fun getEventFromCache(id: String): Flow<Result<Event>> =
        cache.getEventFromCache(id).map { it.asExternalModel() }.asResult()

    override fun getEventsFilter(): Flow<EventsFilter> = flow {
        val filters = EventsFilter(
            sortType = SortType.valueOf(preferences.getSortType().first() ?: SortType.NAME.name),
            city = preferences.getFilterBy().first() ?: Cities.ALL.city
        )
        emit(filters)
    }.flowOn(Dispatchers.IO)

    override suspend fun setEventsFilter(filters: EventsFilter) {
        preferences.saveFilterBy(filter = filters.city)
        preferences.saveSortType(type = filters.sortType.name)
    }
}