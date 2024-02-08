package com.manuelblanco.mobilechallenge.core.database.di

import android.content.Context
import androidx.room.Room
import com.manuelblanco.mobilechallenge.core.database.ChallengeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Manuel Blanco Murillo on 26/6/23.
 */

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesChallengeDatabase(
        @ApplicationContext context: Context,
    ): ChallengeDatabase = Room.databaseBuilder(
        context,
        ChallengeDatabase::class.java,
        "challenge-database",
    ).fallbackToDestructiveMigration()
        .build()
}