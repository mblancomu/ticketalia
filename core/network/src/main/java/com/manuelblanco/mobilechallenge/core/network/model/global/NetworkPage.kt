package com.manuelblanco.mobilechallenge.core.network.model.global

import kotlinx.serialization.Serializable

/**
 * Created by Manuel Blanco Murillo on 29/6/23.
 */

@Serializable
data class NetworkPage(
    val size: Int?,
    val totalElements: Int?,
    val totalPages: Int?,
    val number: Int?
)