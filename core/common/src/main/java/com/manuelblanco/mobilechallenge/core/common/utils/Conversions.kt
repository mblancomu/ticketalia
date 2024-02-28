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
            val minutes = if (time.minute == 0) "00" else time.minute
            formatted = "${time.hour}:${minutes}"
        } catch (_: Exception) {
        }

        return formatted
    }
    return ""
}

fun String.convertToDouble(): Double {
    return if (isNullOrBlank() || isEmpty() || isNullOrEmpty()
    ) {
        0.0
    } else {
        try {
            toDouble()
        } catch (e: Exception) {
            0.0
        }
    }
}