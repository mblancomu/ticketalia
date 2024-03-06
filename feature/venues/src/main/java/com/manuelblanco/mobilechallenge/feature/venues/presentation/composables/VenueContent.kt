package com.manuelblanco.mobilechallenge.feature.venues.presentation.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme
import com.manuelblanco.mobilechallenge.core.designsystem.utils.distanceColors
import com.manuelblanco.mobilechallenge.core.domain.model.Venue
import com.manuelblanco.mobilechallenge.core.testing.data.venueDetail
import com.manuelblanco.mobilechallenge.core.ui.components.FeatureItemList
import com.manuelblanco.mobilechallenge.core.ui.components.NameItemList
import com.manuelblanco.mobilechallenge.core.ui.components.TicketsPoster
import com.manuelblanco.mobilechallenge.feature.venues.R

/**
 * Created by Manuel Blanco Murillo on 7/2/24.
 */

@Composable
fun VenueContent(
    venue: Venue,
    modifier: Modifier = Modifier,
    onVenueClicked: (String) -> Unit = {}
) {
    Card(
        modifier = modifier
            .height(TicketsTheme.dimensions.cardListHeight)
            .padding(
                top = 0.dp,
                start = TicketsTheme.dimensions.paddingMedium,
                end = TicketsTheme.dimensions.paddingMedium,
                bottom = 0.dp
            )
            .clickable { onVenueClicked(venue.id) },
        shape = RoundedCornerShape(size = TicketsTheme.dimensions.roundedCornerItemList),
        elevation = CardDefaults.cardElevation(),
    ) {
        Box {
            TicketsPoster(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                posterPath = venue.imageUrl,
                placeholder = painterResource(id = R.drawable.venue)
            )
            VenueInfo(
                venue = venue,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .height(TicketsTheme.dimensions.cardInfoHeight)
                    .fillMaxWidth()
                    .background(TicketsTheme.colors.primary.copy(alpha = 0.9f))
            )
        }
    }
}

@Composable
private fun VenueInfo(venue: Venue, modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(TicketsTheme.dimensions.paddingSmall),
        modifier = modifier.padding(
            horizontal = TicketsTheme.dimensions.paddingMedium,
            vertical = TicketsTheme.dimensions.paddingMedium
        ),
    ) {
        NameItemList(name = venue.name)
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Column {
                FeatureItemList(
                    Icons.Default.Place,
                    venue.address.plus(venue.city)
                )
                FeatureItemList(
                    Icons.Default.Flag,
                    venue.country
                )
            }
        }
    }
}

@Composable
private fun VenueDistance(distance: Double, modifier: Modifier) {
    val colors = Color.distanceColors(venueDistance = distance)
    val brush = remember(distance) { Brush.horizontalGradient(colors) }
    Text(
        text = distance.toString(),
        style = TicketsTheme.typography.bodyLarge.copy(color = TicketsTheme.colors.surface),
        modifier = modifier
            .background(brush, RoundedCornerShape(50))
            .padding(horizontal = TicketsTheme.dimensions.paddingMediumLarge)
            .shadow(TicketsTheme.dimensions.paddingMedium)
    )
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Preview
@Composable
fun VenueContentComponentPreview(
    venue: Venue = venueDetail.data
) {
    BoxWithConstraints {
        TicketsTheme {
            VenueContent(venue = venue)
        }
    }
}