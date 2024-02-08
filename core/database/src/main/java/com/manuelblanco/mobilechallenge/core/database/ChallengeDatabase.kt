package com.manuelblanco.mobilechallenge.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.manuelblanco.mobilechallenge.core.database.dao.EventDao
import com.manuelblanco.mobilechallenge.core.database.dao.RemoteKeysDao
import com.manuelblanco.mobilechallenge.core.database.dao.VenueDao
import com.manuelblanco.mobilechallenge.core.database.model.EventEntity
import com.manuelblanco.mobilechallenge.core.database.model.RemoteKeyEntity
import com.manuelblanco.mobilechallenge.core.database.model.VenueEntity

/**
 * Created by Manuel Blanco Murillo on 26/6/23.
 */
@Database(
    entities = [
        EventEntity::class,
        VenueEntity::class,
        RemoteKeyEntity::class
    ],
    version = 2,
    exportSchema = true
)
abstract class ChallengeDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
    abstract fun venueDao(): VenueDao
    abstract fun remoteKeyDao(): RemoteKeysDao
}