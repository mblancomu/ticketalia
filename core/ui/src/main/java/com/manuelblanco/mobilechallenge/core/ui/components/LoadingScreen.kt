package com.manuelblanco.mobilechallenge.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme

/**
 * Created by Manuel Blanco Murillo on 16/2/24.
 */

@Composable
fun LoadingScreen(title: String, modifier: Modifier = Modifier){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            modifier = Modifier
                .padding(TicketsTheme.dimensions.paddingMedium),
            text = title
        )

        Progress()
    }
}