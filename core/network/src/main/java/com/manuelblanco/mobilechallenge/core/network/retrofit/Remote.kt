package com.manuelblanco.mobilechallenge.core.network.retrofit

/**
 * Created by Manuel Blanco Murillo on 1/2/24.
 */
interface Remote {

    suspend fun getEvents(
        page: String?,
        size: String?,
        sort: String?,
        countryCode: String?,
        keyword: String?,
    ): NetworkPageEventsResponse

    suspend fun getVenues(
        page: String?,
        size: String?,
        sort: String?,
        countryCode: String?
    ): NetworkPageVenuesResponse
}