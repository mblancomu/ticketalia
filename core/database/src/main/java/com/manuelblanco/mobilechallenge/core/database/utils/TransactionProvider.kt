package com.manuelblanco.mobilechallenge.core.database.utils

import androidx.room.withTransaction
import com.manuelblanco.mobilechallenge.core.database.ChallengeDatabase

/**
 * Created by Manuel Blanco Murillo on 28/6/23.
 */


class TransactionProvider(
    private val db: ChallengeDatabase
) {
    suspend fun <R> runAsTransaction(block: suspend () -> R): R {
        return db.withTransaction(block)
    }
}