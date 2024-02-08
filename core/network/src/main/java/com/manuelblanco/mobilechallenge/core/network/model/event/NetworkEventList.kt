package com.manuelblanco.mobilechallenge.core.network.model.event

import kotlinx.serialization.Serializable

/**
 * Created by Manuel Blanco Murillo on 1/2/24.
 */

@Serializable
data class NetworkEventList(
    val events: List<NetworkEvent>
)