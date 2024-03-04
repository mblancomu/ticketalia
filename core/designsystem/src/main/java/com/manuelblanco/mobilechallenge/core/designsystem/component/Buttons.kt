package com.manuelblanco.mobilechallenge.core.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
            containerColor = TicketsTheme.colors.primary,
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
fun TicketsMultiToggleButton(
    modifier: Modifier = Modifier,
    title: String = "",
    selectedItem: String,
    states: List<String>,
    onSelectedItem: (item: String, index: Int) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        if (title.isNotEmpty()) {
            Text(
                modifier = Modifier.padding(
                    bottom = TicketsTheme.dimensions.paddingLarge
                ),
                textAlign = TextAlign.Start,
                style = TicketsTheme.typography.bodyLarge.copy(color = TicketsTheme.colors.primary),
                text = title
            )
        }

        Box(modifier = Modifier.horizontalScroll(rememberScrollState())) {
            Surface(
                shape = RoundedCornerShape(TicketsTheme.dimensions.paddingMediumDouble),
                modifier = Modifier.wrapContentSize()
            ) {
                Row(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(TicketsTheme.dimensions.paddingMediumDouble))
                        .background(TicketsTheme.colors.outline),
                    horizontalArrangement = Arrangement.Center
                ) {
                    states.forEachIndexed { index, text ->
                        Text(
                            text = text.uppercase(),
                            color = TicketsTheme.colors.onPrimary,
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(TicketsTheme.dimensions.paddingMediumDouble))
                                .clickable {
                                    onSelectedItem(text, index)
                                }
                                .background(
                                    if (text == selectedItem) {
                                        TicketsTheme.colors.primary
                                    } else {
                                        TicketsTheme.colors.outline
                                    }
                                )
                                .padding(
                                    vertical = TicketsTheme.dimensions.paddingMediumDouble,
                                    horizontal = TicketsTheme.dimensions.paddingXLarge,
                                ),
                        )
                    }
                }
            }
        }
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

@Preview
@Composable
fun ToggleButtonPreview() {
    Box(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        Column {
            TicketsMultiToggleButton(
                states = listOf("ALL", "MADRID", "BARCELONA", "SEVILLA", "VALENCIA"),
                selectedItem = "ALL",
                onSelectedItem = { _, _ -> }
            )
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                TicketsButton(onClick = { }, label = "APPLY")
                Spacer(modifier = Modifier.padding(horizontal = 32.dp))
                TicketsButton(onClick = { }, label = "CLEAR")
            }
        }
    }
}