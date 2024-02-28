package com.manuelblanco.mobilechallenge.core.data.model

import com.manuelblanco.mobilechallenge.core.common.utils.locationToString
import com.manuelblanco.mobilechallenge.core.database.model.EventEntity
import com.manuelblanco.mobilechallenge.core.model.data.Location
import com.manuelblanco.mobilechallenge.core.network.model.event.toExternalPlace
import com.manuelblanco.mobilechallenge.core.network.model.event.toPrice
import com.manuelblanco.mobilechallenge.core.network.model.global.toExternalModel
import com.manuelblanco.mobilechallenge.core.network.model.venue.toPlace
import com.manuelblanco.mobilechallenge.core.network.retrofit.NetworkPageEventsResponse
import com.manuelblanco.mobilechallenge.core.data.util.getImageUrlByRatio
import com.manuelblanco.mobilechallenge.core.network.model.event.toCurrency

fun NetworkPageEventsResponse.asEventEntities(): List<EventEntity> {
    val page = this.page?.number

    return this.items?.events?.map {
        EventEntity(
            id = it.id,
            name = it.name ?: "",
            description = it.description ?: "",
            url = it.url ?: "",
            location = locationToString(
                it.location?.toExternalModel() ?: Location(
                    latitude = 0.0,
                    longitude = 0.0
                )
            ),
            imageUrl = if (it.images.isNullOrEmpty()) "" else getImageUrlByRatio(
                images = it.images ?: emptyList()
            ) ?: "",
            dateTime = it.dates?.start?.dateTime ?: "",
            place = if (!it.venues?.venues.isNullOrEmpty()) it.venues?.venues?.firstOrNull()?.toPlace()
                ?: "" else it.place?.toExternalPlace() ?: "",
            city = if (!it.venues?.venues.isNullOrEmpty()) it.venues?.venues?.firstOrNull()?.city?.name
                ?: "" else it.place?.city?.name ?: "",
            country = if (!it.venues?.venues.isNullOrEmpty()) it.venues?.venues?.firstOrNull()?.country?.name
                ?: "" else it.place?.country?.name ?: "",
            segment = it.genre?.firstOrNull()?.segment?.name ?: "",
            genres = it.genre?.firstOrNull()?.genre?.name ?: "",
            price = it.priceRanges?.firstOrNull()?.toPrice() ?: "",
            currency = it.priceRanges?.firstOrNull()?.toCurrency() ?: "",
            page = page ?: 0
        )
    } ?: emptyList()
}