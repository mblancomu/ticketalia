package com.manuelblanco.mobilechallenge.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
    @ColumnInfo(defaultValue = "")
    var url: String
)