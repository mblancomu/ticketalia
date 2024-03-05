package com.manuelblanco.mobilechallenge.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    @ColumnInfo(defaultValue = "")
    val description: String,
    val url: String,
    val location: String,
    val imageUrl: String,
    val dateTime: String,
    @ColumnInfo(defaultValue = "")
    val place: String,
    @ColumnInfo(defaultValue = "")
    val city: String,
    @ColumnInfo(defaultValue = "")
    val country: String,
    @ColumnInfo(defaultValue = "")
    val segment: String,
    @ColumnInfo(defaultValue = "")
    val genres: String,
    @ColumnInfo(defaultValue = "")
    val price: String,
    @ColumnInfo(defaultValue = "")
    val currency: String,
    @ColumnInfo(name = "page")
    var page: Int,
)

