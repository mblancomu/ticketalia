package com.manuelblanco.mobilechallenge.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.manuelblanco.mobilechallenge.core.datastore.ChallengeThemesPreferences.PreferenceKeys.isDarkMode
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Manuel Blanco Murillo on 26/6/23.
 */

@Singleton
class ChallengeThemesPreferences @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "themes")

    private val dataStore = context.dataStore

    private object PreferenceKeys {
        val isDarkMode = booleanPreferencesKey("dark_mode")
    }

    val getTheme: Flow<Boolean?> = dataStore.data
        .map { preferences ->
            preferences[isDarkMode]
        }

    suspend fun saveTheme(isDark: Boolean) {
        dataStore.edit { preferences ->
            preferences[isDarkMode] = isDark
        }
    }
}