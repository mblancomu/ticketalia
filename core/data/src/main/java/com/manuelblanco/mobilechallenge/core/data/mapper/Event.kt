package com.manuelblanco.mobilechallenge.core.data.mapper

import com.manuelblanco.mobilechallenge.core.database.model.EventEntity
import com.manuelblanco.mobilechallenge.core.domain.model.Location
import com.manuelblanco.mobilechallenge.core.network.model.event.toCurrency
import com.manuelblanco.mobilechallenge.core.network.model.event.toExternalPlace
import com.manuelblanco.mobilechallenge.core.network.model.event.toPrice
import com.manuelblanco.mobilechallenge.core.network.model.venue.toPlace
import com.manuelblanco.mobilechallenge.core.network.retrofit.NetworkPageEventsResponse

/**
 * Created by Manuel Blanco Murillo on 5/3/24.
 */

fun EventEntity.asExternalModel() = com.manuelblanco.mobilechallenge.core.domain.model.Event(
    id = id,
    name = name,
    description = description,
    url = url,
    location = stringToLocation(location),
    imageUrl = imageUrl,
    dateTime = dateTime,
    place = place,
    city = city,
    country = country,
    segment = segment,
    genres = genres,
    price = price.convertToDouble(),
    currency = currency
)

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