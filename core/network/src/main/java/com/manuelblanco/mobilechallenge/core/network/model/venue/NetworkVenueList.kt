package com.manuelblanco.mobilechallenge.core.network.model.venue

import kotlinx.serialization.Serializable

/**
 * Created by Manuel Blanco Murillo on 1/2/24.
 */

@Serializable
data class NetworkVenueList(
    val venues: List<NetworkVenue>
)