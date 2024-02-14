package com.manuelblanco.mobilechallenge.core.testing.utils

import android.annotation.SuppressLint
import androidx.paging.DifferCallback
import androidx.paging.NullPaddedList
import androidx.paging.PagingData
import androidx.paging.PagingDataDiffer
import kotlinx.coroutines.test.StandardTestDispatcher

/**
 * Created by Manuel Blanco Murillo on 13/2/24.
 */

/**
 * Extracts the list of data from a PagingData object.
 * Useful for testing transformations on PagingData.
 *
 * flowOf(PagingData.from(listOf(model)).toList() == listOf(model)
 *
 * When nothing else is left, Java reflection will always be there to help us out.
 */
@SuppressLint("RestrictedApi")
suspend fun <T : Any> PagingData<T>.collectDataForTest(): List<T> {
    val dcb = @SuppressLint("RestrictedApi")
    object : DifferCallback {
        override fun onChanged(position: Int, count: Int) {}
        override fun onInserted(position: Int, count: Int) {}
        override fun onRemoved(position: Int, count: Int) {}
    }
    val items = mutableListOf<T>()
    val dif = @SuppressLint("RestrictedApi")
    object : PagingDataDiffer<T>(dcb, StandardTestDispatcher()) {
        override suspend fun presentNewList(
            previousList: NullPaddedList<T>,
            newList: NullPaddedList<T>,
            lastAccessedIndex: Int,
            onListPresentable: () -> Unit
        ): Int? {
            for (idx in 0 until newList.size)
                items.add(newList.getFromStorage(idx))
            onListPresentable()
            return null
        }
    }
    dif.collectFrom(this)
    return items
}