package com.manuelblanco.mobilechallenge.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme

/**
 * Created by Manuel Blanco Murillo on 12/2/24.
 */

@Composable
fun CardContentDetail(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .padding(top = TicketsTheme.dimensions.cardDetailPadding)
            .fillMaxSize(),
        colors = CardDefaults.cardColors(containerColor = TicketsTheme.colors.primary),
        shape = RoundedCornerShape(
            topStart = TicketsTheme.dimensions.roundedCornerDetail,
            topEnd = TicketsTheme.dimensions.roundedCornerDetail
        ),
        elevation = CardDefaults.cardElevation(),
    ) {
        Column(
            modifier = Modifier
                .padding(TicketsTheme.dimensions.paddingMediumDouble),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}