package com.manuelblanco.mobilechallenge.core.datastore.di

import com.manuelblanco.mobilechallenge.core.datastore.Preferences
import com.manuelblanco.mobilechallenge.core.datastore.TicketsPreferences
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferencesModule {

    @Binds
    abstract fun bindCache(cache: TicketsPreferences): Preferences
}