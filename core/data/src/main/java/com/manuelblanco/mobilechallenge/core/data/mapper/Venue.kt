package com.manuelblanco.mobilechallenge.core.data.mapper

import com.manuelblanco.mobilechallenge.core.database.model.VenueEntity
import com.manuelblanco.mobilechallenge.core.domain.model.Location
import com.manuelblanco.mobilechallenge.core.network.model.global.toFinalAddress
import com.manuelblanco.mobilechallenge.core.network.retrofit.NetworkPageVenuesResponse

/**
 * Created by Manuel Blanco Murillo on 5/3/24.
 */

fun VenueEntity.asExternalModel() = com.manuelblanco.mobilechallenge.core.domain.model.Venue(
    id = id,
    name = name,
    description = description,
    location = stringToLocation(location),
    imageUrl = imageUrl,
    country = country,
    city = city,
    address = address,
    distance = distance,
    url = url
)

fun NetworkPageVenuesResponse.asVenueEntities(): List<VenueEntity> {
    val page = this.page?.number

    return this.items?.venues?.map {
        VenueEntity(
            id = it.id,
            name = it.name ?: "",
            description = it.description ?: "",
            location = locationToString(
                it.location?.toExternalModel() ?: Location(
                    latitude = 0.0,
                    longitude = 0.0
                )
            ),
            imageUrl = if (it.images.isNullOrEmpty()) "" else getImageUrlByRatio(images = it.images ?: emptyList()) ?: "",
            country = it.country?.name ?: "",
            city = it.city?.name ?: "",
            address = it.address?.toFinalAddress() ?: "",
            distance = "${it.distance} ${it.units}",
            page = page ?: 0,
            url = it.url ?: ""
        )
    } ?: emptyList()
}