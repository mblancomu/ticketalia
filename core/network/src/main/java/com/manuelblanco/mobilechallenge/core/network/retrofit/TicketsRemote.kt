package com.manuelblanco.mobilechallenge.core.network.retrofit

import com.manuelblanco.mobilechallenge.core.network.BuildConfig
import javax.inject.Inject

/**
 * Created by Manuel Blanco Murillo on 1/2/24.
 */
class TicketsRemote @Inject constructor(
    private val ticketsApi: RetrofitNetworkApi
) : Remote {
    override suspend fun getEvents(
        page: String?,
        size: String?,
        sort: String?,
        countryCode: String?,
        keyword: String?,
    ): NetworkPageEventsResponse =
        ticketsApi.getEvents(page, size, sort, countryCode, keyword, BuildConfig.CONSUMER_KEY)

    override suspend fun getVenues(
        page: String?,
        size: String?,
        sort: String?,
        countryCode: String?
    ): NetworkPageVenuesResponse =
        ticketsApi.getVenues(page, size, sort, BuildConfig.CONSUMER_KEY, countryCode)
}