package com.manuelblanco.mobilechallenge.core.data.util

import com.manuelblanco.mobilechallenge.core.network.model.global.RATIO
import com.manuelblanco.mobilechallenge.core.network.model.global.NetworkImages

/**
 * Created by Manuel Blanco Murillo on 11/2/24.
 */

fun getImageUrlByRatio(images: List<NetworkImages>): String {
    val imageUrl = images.first {
        it.ratio == RATIO.LANDSCAPE1 || it.ratio == RATIO.LANDSCAPE2
    }.url
    return imageUrl.ifEmpty { images.first().url }
}