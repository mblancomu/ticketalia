package com.manuelblanco.mobilechallenge.core.network.model.event

import kotlinx.serialization.Serializable

/**
 * Created by Manuel Blanco Murillo on 1/2/24.
 */
@Serializable
data class NetworkEventClassification(
    val segment: NetworkEventSegment? = null,
    val genre: NetworkEventGenre? = null
)
