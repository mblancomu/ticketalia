package com.manuelblanco.mobilechallenge.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme

/**
 * Created by Manuel Blanco Murillo on 23/2/24.
 */

@Composable
fun TicketsSearchBar(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    placeHolder: String = "",
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onFilterClicked: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(TicketsTheme.dimensions.roundedCornerItemList)),
        contentAlignment = Alignment.Center
    ) {

        OutlinedTextField(
            modifier = Modifier
                .background(color = TicketsTheme.colors.secondary)
                .semantics { contentDescription = "Search View" }
                .fillMaxWidth()
                .height(TicketsTheme.dimensions.searchBarHeight),
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            placeholder = {
                Text(
                    text = placeHolder,
                    color = TicketsTheme.colors.outline,
                    style = TicketsTheme.typography.bodyLarge,
                )
            },
            leadingIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = TicketsTheme.colors.onSecondary,
                        modifier = Modifier.size(TicketsTheme.dimensions.iconSizeSearchBar)
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    modifier = Modifier.semantics { contentDescription = "Icon Button Search Bar" },
                    onClick = {
                    if (text.isNotBlank()) {
                        onCloseClicked()
                    } else {
                        onFilterClicked()
                    }
                }) {
                    if (text.isNotBlank()) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Icon Clear",
                            tint = TicketsTheme.colors.onSecondary,
                            modifier = Modifier.size(TicketsTheme.dimensions.iconSizeSearchBar)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.FilterList,
                            contentDescription = "Icon Filter",
                            tint = TicketsTheme.colors.onSecondary,
                            modifier = Modifier.size(TicketsTheme.dimensions.iconSizeSearchBar)
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),
            singleLine = true,
            textStyle = TicketsTheme.typography.bodyLarge.copy(color = TicketsTheme.colors.onSecondary),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                cursorColor = TicketsTheme.colors.primary
            ),
        )
        SearchBarDivider(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(horizontal = 50.dp)
        )

    }
}

@Composable
fun SearchBarDivider(
    modifier: Modifier = Modifier
) {
    HorizontalDivider(
        modifier = modifier
            .width(1.dp),
        thickness = 20.dp,
        color = TicketsTheme.colors.onSecondary
    )
}

@Composable
@Preview()
fun SearchPreview() {
    TicketsTheme {
        Box(
            modifier = Modifier
                .background(Color.White)
                .padding(40.dp)
                .fillMaxWidth()
                .height(100.dp),
            contentAlignment = Alignment.Center
        ) {
            TicketsSearchBar(
                text = "",
                onTextChange = {},
                placeHolder = "Search something",
                onCloseClicked = {},
                onSearchClicked = {},
                onFilterClicked = {}
            )
        }
    }
}