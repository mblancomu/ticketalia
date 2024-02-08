package com.manuelblanco.mobilechallenge.core.network.model.global

import kotlinx.serialization.Serializable

/**
 * Created by Manuel Blanco Murillo on 27/6/23.
 */

@Serializable
data class NetworkImages(
    val url: String,
    val width: Int,
    val height: Int
)