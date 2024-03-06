package com.manuelblanco.mobilechallenge.core.data.mapper

import com.manuelblanco.mobilechallenge.core.domain.model.Page
import com.manuelblanco.mobilechallenge.core.network.model.global.NetworkPage

/**
 * Created by Manuel Blanco Murillo on 5/3/24.
 */

fun NetworkPage.toExternalModel() = Page(
    totalElements = totalElements,
    totalPages = totalPages,
    currentPage = number
)