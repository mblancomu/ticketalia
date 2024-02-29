package com.manuelblanco.mobilechallenge.feature.venues.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manuelblanco.mobilechallenge.core.designsystem.component.TicketsOutlinedButton
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme
import com.manuelblanco.mobilechallenge.core.model.data.Venue
import com.manuelblanco.mobilechallenge.core.model.data.toGoogleUri
import com.manuelblanco.mobilechallenge.core.testing.data.venueDetail
import com.manuelblanco.mobilechallenge.core.ui.components.AnimatedTextVisibility
import com.manuelblanco.mobilechallenge.core.ui.components.CardContentDetail
import com.manuelblanco.mobilechallenge.core.ui.components.DescriptionTextDetail
import com.manuelblanco.mobilechallenge.core.ui.components.HeaderTitleDetail
import com.manuelblanco.mobilechallenge.core.ui.components.TicketsPoster
import com.manuelblanco.mobilechallenge.feature.venues.R
import com.manuelblanco.mobilechallenge.feature.venues.presentation.VenueDetailContract

/**
 * Created by Manuel Blanco Murillo on 7/2/24.
 */

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun VenueDetailContent(
    venue: Venue?,
    venueTitle: String,
    onSendEvent: (VenueDetailContract.Event) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        TicketsPoster(
            posterPath = venue?.imageUrl ?: "",
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .height(TicketsTheme.dimensions.posterDetailHeight),
            placeholder = painterResource(id = R.drawable.venue)
        )
        CardContentDetail(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            HeaderTitleDetail(title = venueTitle)
            VenueDetailAnimatedColumn(
                venue?.address.toString(),
                venue?.city.toString(),
                venue?.country.toString()
            )
            HorizontalDivider(thickness = 1.dp, color = TicketsTheme.colors.secondary)
            VenueDetailButtonsRow(
                info = venue?.url,
                uriLocation = venue?.location?.toGoogleUri(),
                onClickInfo = {
                    onSendEvent(VenueDetailContract.Event.LinkButtonClicked)
                },
                onClickLocation = {
                    onSendEvent(VenueDetailContract.Event.DirectionButtonClicked)
                }
            )
            DescriptionTextDetail(text = venue?.description.toString())
        }
    }
}

@Composable
private fun VenueDetailAnimatedColumn(
    address: String,
    city: String,
    country: String
) {
    Column(
        modifier = Modifier.padding(
            top = TicketsTheme.dimensions.paddingMediumDouble,
            bottom = TicketsTheme.dimensions.paddingMediumDouble
        ),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedTextVisibility(info = address, icon = Icons.Filled.Home)
        AnimatedTextVisibility(info = city, icon = Icons.Filled.LocationCity)
        AnimatedTextVisibility(info = country, icon = Icons.Filled.Flag)
    }
}

@Composable
private fun VenueDetailButtonsRow(
    info: String?,
    uriLocation: String?,
    onClickInfo: () -> Unit,
    onClickLocation: () -> Unit
) {
    Row(
        modifier = Modifier.padding(
            top = TicketsTheme.dimensions.paddingMediumDouble,
            bottom = TicketsTheme.dimensions.paddingMediumDouble
        ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        info?.let { uri ->
            if (uri.isNotEmpty()) {
                TicketsOutlinedButton(
                    modifier = Modifier
                        .padding(end = TicketsTheme.dimensions.paddingMedium)
                        .semantics { contentDescription = "Button Info" }
                        .width(TicketsTheme.dimensions.buttonDetailWidth),
                    onClick = {
                        onClickInfo()
                    },
                    label = stringResource(id = R.string.button_info),
                    enabled = true,
                )
            }
        }
        uriLocation?.let { uri ->
            if (uri.isNotEmpty()) {
                TicketsOutlinedButton(
                    modifier = Modifier
                        .padding(start = TicketsTheme.dimensions.paddingMedium)
                        .semantics { contentDescription = "Button Location" }
                        .width(TicketsTheme.dimensions.buttonDetailWidth),
                    onClick = {
                        onClickLocation()
                    },
                    label = stringResource(id = R.string.button_location),
                    enabled = true,
                )
            }
        }
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Preview
@Composable
fun VenueDetailContentComponentPreview(
    venue: Venue = venueDetail.data
) {
    BoxWithConstraints {
        TicketsTheme {
            VenueDetailContent(
                venue = venue,
                venueTitle = venue.name,
                onSendEvent = {},
            )
        }
    }
}