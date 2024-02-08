package com.manuelblanco.mobilechallenge.core.ui.utils

/**
 * Created by Manuel Blanco Murillo on 28/8/22.
 */
sealed class UIEvents{
    data class ShowSnackBar(val msg:String): UIEvents()
}
