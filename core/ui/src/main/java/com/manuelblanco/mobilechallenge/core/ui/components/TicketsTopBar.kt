package com.manuelblanco.mobilechallenge.core.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme
import com.manuelblanco.mobilechallenge.core.ui.R

/**
 * Created by Manuel Blanco Murillo on 31/1/24.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketsTopBar(
    title: String = "",
    onBack: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    searchBar: @Composable (() -> Unit?)? = null,
    containerColor: Color = TicketsTheme.colors.primary,
    isCentered: Boolean,
    isNavigable: Boolean = false
) {
    if (isCentered) {
        CenterAlignedTopAppBar(
            modifier = Modifier.semantics { contentDescription = "Top bar" },
            title = {
                if (searchBar != null) {
                    searchBar()
                } else {
                    Text(
                        text = stringResource(id = R.string.name_app),
                        color = TicketsTheme.colors.surface,
                        textAlign = TextAlign.Center
                    )
                }
            },
            navigationIcon = {
                if (isNavigable) {
                    BackArrow { onBack() }
                }
            },
            actions = { actions() },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = containerColor)
        )
    } else {
        TopAppBar(
            title = {
                Text(
                    text = title,
                    color = TicketsTheme.colors.surface,
                    textAlign = TextAlign.Center
                )
            },
            navigationIcon = {
                BackArrow { onBack() }
            },
            actions = { actions() },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = containerColor)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun TicketsTopBarComponentPreview() {
    TicketsTheme {
        TicketsTopBar("Events", isCentered = true)
    }
}

@Composable
private fun BackArrow(onBack: () -> Unit = {}) {
    FilledIconButton(
        modifier = Modifier.semantics { contentDescription = "Back Button" },
        onClick = { onBack() },
        colors = IconButtonDefaults.iconButtonColors(containerColor = TicketsTheme.colors.primary)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBackIosNew,
            contentDescription = "Back Arrow",
            tint = TicketsTheme.colors.surface
        )
    }
}