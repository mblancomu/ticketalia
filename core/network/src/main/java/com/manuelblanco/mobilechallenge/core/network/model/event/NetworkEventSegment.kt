package com.manuelblanco.mobilechallenge.core.network.model.event

import kotlinx.serialization.Serializable

/**
 * Created by Manuel Blanco Murillo on 12/2/24.
 */

@Serializable
data class NetworkEventSegment(
    val id: String,
    val name: String
)
