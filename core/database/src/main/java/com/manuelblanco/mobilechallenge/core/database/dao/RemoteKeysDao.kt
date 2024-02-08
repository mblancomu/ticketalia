package com.manuelblanco.mobilechallenge.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manuelblanco.mobilechallenge.core.database.model.RemoteKeyEntity

/**
 * Created by Manuel Blanco Murillo on 28/6/23.
 */

@Dao
interface RemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeyEntity>)

    @Query("Select * From remote_key Where id = :id")
    suspend fun getRemoteKeyByProfileId(id: String): RemoteKeyEntity?

    @Query("SELECT * FROM remote_key WHERE id = :id")
    suspend fun remoteKeysProfileId(id: String): RemoteKeyEntity?

    @Query("Select created_at From remote_key Order By created_at DESC LIMIT 1")
    suspend fun getCreationTime(): Long?

    @Query("Delete From remote_key")
    suspend fun clearRemoteKeys()
}