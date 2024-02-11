package com.manuelblanco.mobilechallenge.core.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme

/**
 * Created by Manuel Blanco Murillo on 25/6/23.
 */

@Composable
fun TicketsButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    leadingIcon: @Composable (() -> Unit)? = null,
    label: String = ""
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = TicketsTheme.colors.onBackground,
        ),
        contentPadding = contentPadding
    ) {
        TicketsButtonContent(
            label = label,
            leadingIcon = leadingIcon
        )
    }
}

@Composable
fun TicketsOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.TextButtonContentPadding,
    leadingIcon: @Composable (() -> Unit)? = null,
    label: String = ""
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = TicketsTheme.colors.onBackground,
        ),
        border = BorderStroke(
            width = 1.dp,
            color = if (enabled) {
                TicketsTheme.colors.secondary
            } else {
                TicketsTheme.colors.onSecondary.copy(
                    alpha = 0.12f,
                )
            },
        ),
        contentPadding = contentPadding
    ) {
        TicketsButtonContent(
            label = label,
            leadingIcon = leadingIcon
        )
    }
}

@Composable
fun TicketsTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    leadingIcon: @Composable (() -> Unit)? = null,
    label: String = ""
) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.textButtonColors(
            contentColor = TicketsTheme.colors.onBackground,
        ),
        contentPadding = contentPadding
    ) {
        TicketsButtonContent(
            label = label,
            leadingIcon = leadingIcon
        )
    }
}

@Composable
private fun TicketsButtonContent(
    label: String,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    if (leadingIcon != null) {
        Box(Modifier.sizeIn(maxHeight = ButtonDefaults.IconSize)) {
            leadingIcon()
        }
    }
    Box(
        Modifier
            .padding(
                start = if (leadingIcon != null) {
                    ButtonDefaults.IconSpacing
                } else {
                    0.dp
                },
            ),
    ) {
        Text(
            text = label,
            color = TicketsTheme.colors.secondary,
            style = TicketsTheme.typography.titleMedium
        )
    }
}