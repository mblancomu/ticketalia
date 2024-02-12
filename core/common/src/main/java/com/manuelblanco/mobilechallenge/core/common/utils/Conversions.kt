package com.manuelblanco.mobilechallenge.core.common.utils

import com.manuelblanco.mobilechallenge.core.model.data.Location
import java.time.ZonedDateTime

fun stringToLocation(location: String): Location {
    val coordinates = location.split(",").map { it.trim().toDouble() }
    return Location(latitude = coordinates[0], longitude = coordinates[1])
}

fun locationToString(location: Location): String {
    return "${location.latitude},${location.longitude}"
}

fun formattedDate(dateTime: String?): String {
    dateTime?.let {
        val formatted = ZonedDateTime.parse(dateTime)
        return "${formatted.dayOfMonth}-${formatted.monthValue}-${formatted.year}"
    }
    return ""
}

fun formattedTime(dateTime: String?): String {
    dateTime?.let {
        val formatted = ZonedDateTime.parse(dateTime)
        return "${formatted.hour}:${formatted.minute}"
    }
    return ""
}