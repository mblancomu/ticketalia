package com.manuelblanco.mobilechallenge.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.manuelblanco.mobilechallenge.core.common.utils.stringToLocation
import com.manuelblanco.mobilechallenge.core.model.data.Venue

@Entity(tableName = "venues")
data class VenueEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    @ColumnInfo(defaultValue = "")
    val description: String,
    val imageUrl: String,
    val location: String,
    val country: String,
    val city: String,
    @ColumnInfo(defaultValue = "")
    val address: String,
    val distance: String,
    @ColumnInfo(name = "page")
    var page: Int,
)

fun VenueEntity.asExternalModel() = Venue(
    id = id,
    name = name,
    description = description,
    location = stringToLocation(location),
    imageUrl = imageUrl,
    country = country,
    city = city,
    address = address,
    distance = distance
)