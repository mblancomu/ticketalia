package com.manuelblanco.mobilechallenge.core.network.model.event

import kotlinx.serialization.Serializable

/**
 * Created by Manuel Blanco Murillo on 27/6/23.
 */

@Serializable
data class NetworkEventDates(
    val start: NetworkEventStart,
    val status: NetworkEventStatus
)