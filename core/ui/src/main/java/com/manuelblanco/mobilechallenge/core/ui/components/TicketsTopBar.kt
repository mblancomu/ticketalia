package com.manuelblanco.mobilechallenge.core.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.manuelblanco.mobilechallenge.core.ui.R

/**
 * Created by Manuel Blanco Murillo on 31/1/24.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketsTopBar(
    title: String = stringResource(id = R.string.name_app),
    onBack: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    isCentered: Boolean,
    isNavigable: Boolean = false
) {
    if (isCentered) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = title,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            },
            navigationIcon = {
                if (isNavigable){
                    BackArrow { onBack() }
                }
            },
            actions = { actions() },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue)
        )
    } else {
        TopAppBar(
            title = { Text(text = title, color = Color.White, textAlign = TextAlign.Center) },
            navigationIcon = {
                BackArrow { onBack() }
            },
            actions = { actions() },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun TicketsTopBarPreview() {
    TicketsTopBar("Events", isCentered = true)
}

@Composable
private fun BackArrow(onBack: () -> Unit = {},){
    IconButton(onClick = { onBack() }) {
        Icon(
            imageVector = Icons.Default.ArrowBackIos,
            contentDescription = "Back Arrow",
            tint = Color.White
        )
    }
}