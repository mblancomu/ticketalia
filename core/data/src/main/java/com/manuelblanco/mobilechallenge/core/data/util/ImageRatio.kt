package com.manuelblanco.mobilechallenge.core.data.util

import com.manuelblanco.mobilechallenge.core.network.model.global.NetworkImages
import com.manuelblanco.mobilechallenge.core.network.model.global.RATIO

/**
 * Created by Manuel Blanco Murillo on 11/2/24.
 */

fun getImageUrlByRatio(images: List<NetworkImages>?): String {
    var imageUrl = ""
    if (!images.isNullOrEmpty()) {
        val filterList =
            images.filter { it.ratio != null && it.ratio == RATIO.LANDSCAPE1 || it.ratio == RATIO.LANDSCAPE2 }
        if (filterList.isNotEmpty()) {
            imageUrl = filterList.map { it.url }.first()
        }

        if (imageUrl.isEmpty()) {
            imageUrl = images.first().url
        }
    }

    return imageUrl
}