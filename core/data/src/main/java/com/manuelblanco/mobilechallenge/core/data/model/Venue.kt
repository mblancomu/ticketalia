package com.manuelblanco.mobilechallenge.core.data.model

import com.manuelblanco.mobilechallenge.core.common.utils.locationToString
import com.manuelblanco.mobilechallenge.core.database.model.VenueEntity
import com.manuelblanco.mobilechallenge.core.model.data.Location
import com.manuelblanco.mobilechallenge.core.network.model.global.toExternalModel
import com.manuelblanco.mobilechallenge.core.network.model.global.toFinalAddress
import com.manuelblanco.mobilechallenge.core.network.retrofit.NetworkPageVenuesResponse
import com.manuelblanco.mobilechallenge.core.data.util.getImageUrlByRatio

fun NetworkPageVenuesResponse.asVenueEntities(): List<VenueEntity> {
    val page = this.page.number

    return this.items.venues.map {
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
    }
}