package com.manuelblanco.mobilechallenge.core.datastore

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Manuel Blanco Murillo on 29/2/24.
 */
class TicketsPreferences @Inject constructor(
    private val dataStore: TicketsDataStore
) : Preferences {
    override fun getSortType(): Flow<String?> {
        return dataStore.getSortType
    }

    override fun getFilterBy(): Flow<String?> {
        return dataStore.getFilterBy
    }

    override suspend fun saveSortType(type: String) {
        dataStore.saveSortType(type)
    }

    override suspend fun saveFilterBy(filter: String) {
        dataStore.saveFilterBy(filter)
    }
}