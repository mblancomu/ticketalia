package com.manuelblanco.mobilechallenge.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.manuelblanco.mobilechallenge.core.datastore.TicketsDataStore.PreferenceKeys.filterBy
import com.manuelblanco.mobilechallenge.core.datastore.TicketsDataStore.PreferenceKeys.sortType
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Manuel Blanco Murillo on 26/6/23.
 */

@Singleton
class TicketsDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "filters")

    private val dataStore = context.dataStore

    private object PreferenceKeys {
        val sortType = stringPreferencesKey("sort_type")
        val filterBy = stringPreferencesKey("filter_by")

    }

    val getSortType: Flow<String?> = dataStore.data
        .map { preferences ->
            preferences[sortType]
        }

    val getFilterBy: Flow<String?> = dataStore.data
        .map { preferences ->
            preferences[filterBy]
        }

    suspend fun saveSortType(type: String) {
        dataStore.edit { preferences ->
            preferences[sortType] = type
        }
    }

    suspend fun saveFilterBy(filter: String) {
        dataStore.edit { preferences ->
            preferences[filterBy] = filter
        }
    }
}