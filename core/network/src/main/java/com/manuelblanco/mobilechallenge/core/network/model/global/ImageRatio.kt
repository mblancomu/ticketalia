package com.manuelblanco.mobilechallenge.core.network.model.global

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Manuel Blanco Murillo on 11/2/24.
 */

@Serializable
enum class RATIO {
    @SerialName("3_2")
    LANDSCAPE1,

    @SerialName("4_3")
    LANDSCAPE2,

    @SerialName("16_9")
    PORTRAIT
}