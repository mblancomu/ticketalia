package com.manuelblanco.mobilechallenge.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme

/**
 * Created by Manuel Blanco Murillo on 31/1/24.
 */

@Composable
fun ErrorRow(title: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Filled.ErrorOutline,
            contentDescription = "Error icon",
            modifier = Modifier.size(40.dp),
        )
        Text(title)
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ErrorRowComponentPreview() {
    TicketsTheme {
        ErrorRow(title = "Some Error!")
    }
}