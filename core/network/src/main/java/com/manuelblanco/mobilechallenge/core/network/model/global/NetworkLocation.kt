package com.manuelblanco.mobilechallenge.core.network.model.global

import com.manuelblanco.mobilechallenge.core.model.data.Location
import kotlinx.serialization.Serializable

/**
 * Created by Manuel Blanco Murillo on 27/6/23.
 */

@Serializable
data class NetworkLocation(
    val longitude: Double,
    val latitude: Double
)

fun NetworkLocation.toExternalModel() = Location(latitude, longitude)