package com.manuelblanco.mobilechallenge.core.datastore

import kotlinx.coroutines.flow.Flow

/**
 * Created by Manuel Blanco Murillo on 29/2/24.
 */
interface Preferences {
    fun getSortType(): Flow<String?>
    fun getFilterBy(): Flow<String?>
    suspend fun saveSortType(type: String)
    suspend fun saveFilterBy(filter: String)
}