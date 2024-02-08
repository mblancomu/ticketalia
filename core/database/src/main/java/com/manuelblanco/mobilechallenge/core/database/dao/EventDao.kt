package com.manuelblanco.mobilechallenge.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manuelblanco.mobilechallenge.core.database.model.EventEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Manuel Blanco Murillo on 26/6/23.
 */

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(events: List<EventEntity>)

    @Query(value = "SELECT * FROM events WHERE id = :eventId")
    fun getEventEntity(eventId: String): Flow<EventEntity>

    @Query(value = "SELECT * FROM events Order By name LIMIT :limit OFFSET :offset")
    fun getEventEntities(limit: Int, offset: Int): Flow<List<EventEntity>>

    @Query(value = "SELECT * FROM events WHERE city = :city Order By name LIMIT :limit OFFSET :offset")
    fun getEventEntitiesByCity(city: String, limit: Int, offset: Int): Flow<List<EventEntity>>

    @Query(value = "SELECT * FROM events WHERE genres LIKE '%' || :type || '%' Order By name LIMIT :limit OFFSET :offset")
    fun getEventEntitiesByType(type: String, limit: Int, offset: Int): Flow<List<EventEntity>>

    @Query(value = "SELECT * FROM events WHERE country = :country Order By name LIMIT :limit OFFSET :offset")
    fun getEventEntitiesByCountry(country: String, limit: Int, offset: Int): Flow<List<EventEntity>>

    @Query(value = "DELETE FROM events")
    suspend fun deleteAll()

    @Query("SELECT count(*) FROM events")
    fun getCount(): Flow<Int>

    @Query("SELECT EXISTS(SELECT * FROM events WHERE id = :id)")
    fun isEventIsExist(id: String) : Boolean
}