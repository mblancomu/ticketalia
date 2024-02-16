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
    var formatted = ""
    dateTime?.let {
        try {
            val date = ZonedDateTime.parse(it)
            formatted = "${date.dayOfMonth}-${date.monthValue}-${date.year}"
        } catch (_: Exception) {
        }

        return formatted
    }
    return ""
}

fun formattedTime(dateTime: String?): String {
    var formatted = ""
    dateTime?.let {
        try {
            val time = ZonedDateTime.parse(it)
            formatted = "${time.hour}:${time.minute}"
        } catch (_: Exception) {
        }

        return formatted
    }
    return ""
}