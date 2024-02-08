package com.manuelblanco.mobilechallenge.core.network.model.global

import com.manuelblanco.mobilechallenge.core.model.data.Page
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

fun NetworkPage.toExternalModel() = Page(
    totalElements = totalElements,
    totalPages = totalPages,
    currentPage = number
)