package com.manuelblanco.mobilechallenge.core.common.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat

/**
 * Created by Manuel Blanco Murillo on 12/2/24.
 */

@SuppressLint("QueryPermissionsNeeded")
fun launchGoogleMaps(uri: String, context: Context) {
    val gmmIntentUri = Uri.parse(uri)
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage("com.google.android.apps.maps")
    ContextCompat.startActivity(context, mapIntent, null)
}