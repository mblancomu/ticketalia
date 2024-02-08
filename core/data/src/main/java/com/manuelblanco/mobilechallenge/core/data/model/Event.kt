package com.manuelblanco.mobilechallenge.core.data.model

import com.manuelblanco.mobilechallenge.core.common.utils.locationToString
import com.manuelblanco.mobilechallenge.core.database.model.EventEntity
import com.manuelblanco.mobilechallenge.core.model.data.Location
import com.manuelblanco.mobilechallenge.core.network.model.event.toExternalPlace
import com.manuelblanco.mobilechallenge.core.network.model.event.toPriceRange
import com.manuelblanco.mobilechallenge.core.network.model.global.toExternalModel
import com.manuelblanco.mobilechallenge.core.network.model.venue.toPlace
import com.manuelblanco.mobilechallenge.core.network.retrofit.NetworkPageEventsResponse

fun NetworkPageEventsResponse.asEventEntities(): List<EventEntity> {
    val page = this.page.number

    return this.items.events.map {
        EventEntity(
            id = it.id,
            name = it.name ?: "",
            description = it.description ?: "",
            location = locationToString(
                it.location?.toExternalModel() ?: Location(
                    latitude = 0.0,
                    longitude = 0.0
                )
            ),
            imageUrl = if (!it.images.isNullOrEmpty()) it.images?.get(0)?.url ?: "" else "",
            dateTime = it.dates?.start?.dateTime ?: "",
            place = if (!it.venues?.venues.isNullOrEmpty()) it.venues?.venues?.get(0)?.toPlace() ?: "" else it.place?.toExternalPlace() ?: "",
            city = if (!it.venues?.venues.isNullOrEmpty())  it.venues?.venues?.get(0)?.city?.name ?: "" else it.place?.city?.name ?: "",
            country = if (!it.venues?.venues.isNullOrEmpty()) it.venues?.venues?.get(0)?.country?.name ?: "" else it.place?.country?.name ?: "" ,
            genres = it.genre?.joinToString(separator = ",") ?: "",
            prices = it.priceRanges?.toPriceRange() ?: "",
            page = page ?: 0
        )
    }
}