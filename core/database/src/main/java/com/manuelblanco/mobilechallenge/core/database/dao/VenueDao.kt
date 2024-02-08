package com.manuelblanco.mobilechallenge.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manuelblanco.mobilechallenge.core.database.model.VenueEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Manuel Blanco Murillo on 26/6/23.
 */

@Dao
interface VenueDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(venues: List<VenueEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertVenue(venue: VenueEntity)

    @Query(value = "SELECT * FROM venues WHERE id = :venueId")
    fun getVenueEntity(venueId: String): Flow<VenueEntity>

    @Query(value = "SELECT * FROM venues Order By page")
    fun getVenueEntities(): PagingSource<Int, VenueEntity>

    @Query(value = "DELETE FROM venues")
    suspend fun deleteAll()

    @Query("SELECT count(*) FROM venues")
    fun getCount(): Flow<Int>

    @Query("SELECT EXISTS(SELECT * FROM venues WHERE id = :id)")
    fun isVenueIsExist(id: String) : Boolean
}