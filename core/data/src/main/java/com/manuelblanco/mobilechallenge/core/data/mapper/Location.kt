package com.manuelblanco.mobilechallenge.core.data.mapper

import com.manuelblanco.mobilechallenge.core.network.model.global.NetworkLocation

/**
 * Created by Manuel Blanco Murillo on 5/3/24.
 */

fun NetworkLocation.toExternalModel() =
    com.manuelblanco.mobilechallenge.core.domain.model.Location(latitude, longitude)