package com.manuelblanco.mobilechallenge.feature.events.presentation.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manuelblanco.mobilechallenge.core.designsystem.component.TicketsOutlinedButton
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme
import com.manuelblanco.mobilechallenge.core.domain.model.Event
import com.manuelblanco.mobilechallenge.core.domain.model.toGoogleUri
import com.manuelblanco.mobilechallenge.core.testing.data.eventDetail
import com.manuelblanco.mobilechallenge.core.ui.components.CardContentDetail
import com.manuelblanco.mobilechallenge.core.ui.components.CircleInfoAnimated
import com.manuelblanco.mobilechallenge.core.ui.components.DescriptionTextDetail
import com.manuelblanco.mobilechallenge.core.ui.components.HeaderTitleDetail
import com.manuelblanco.mobilechallenge.core.ui.components.TicketsPoster
import com.manuelblanco.mobilechallenge.feature.events.R
import com.manuelblanco.mobilechallenge.feature.events.presentation.contracts.EventDetailContract

/**
 * Created by Manuel Blanco Murillo on 7/2/24.
 */

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EventDetailContent(
    event: Event?,
    eventTitle: String,
    onSendEvent: (EventDetailContract.Event) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        TicketsPoster(
            posterPath = event?.imageUrl ?: "", modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .height(TicketsTheme.dimensions.posterDetailHeight)
        )
        CardContentDetail(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            HeaderTitleDetail(title = eventTitle)
            EventDetailAnimatedRow(
                "${event?.price} ${event?.currency}",
                event?.segment.toString(),
                event?.genres.toString()
            )
            HorizontalDivider(thickness = 1.dp, color = TicketsTheme.colors.secondary)
            EventDetailButtonsRow(
                ticketUrl = event?.url,
                uriLocation = event?.location?.toGoogleUri(),
                onClickTicket = {
                    onSendEvent(EventDetailContract.Event.Link)
                },
                onClickLocation = {
                    onSendEvent(EventDetailContract.Event.Direction)
                }
            )
            DescriptionTextDetail(text = event?.description.toString())
        }
    }
}

@Composable
private fun EventDetailAnimatedRow(
    price: String,
    segment: String,
    genre: String
) {
    Row(
        modifier = Modifier.padding(
            top = TicketsTheme.dimensions.paddingMediumDouble,
            bottom = TicketsTheme.dimensions.paddingMediumDouble
        ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircleInfoAnimated(
            info = price,
            subtitle = stringResource(id = R.string.subtitle_price)
        )
        CircleInfoAnimated(
            info = segment,
            subtitle = stringResource(id = R.string.subtitle_type)
        )
        CircleInfoAnimated(
            info = genre,
            subtitle = stringResource(id = R.string.subtitle_genre)
        )
    }
}

@Composable
private fun EventDetailButtonsRow(
    ticketUrl: String?,
    uriLocation: String?,
    onClickTicket: () -> Unit,
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
        ticketUrl?.let { uri ->
            if (uri.isNotEmpty()) {
                TicketsOutlinedButton(
                    modifier = Modifier
                        .padding(end = TicketsTheme.dimensions.paddingMedium)
                        .semantics { contentDescription = "Button Get Tickets" }
                        .width(TicketsTheme.dimensions.buttonDetailWidth),
                    onClick = {
                        onClickTicket()
                    },
                    label = stringResource(id = R.string.button_tickets),
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
fun EventDetailContentComponentPreview(
    event: Event = eventDetail.data
) {
    BoxWithConstraints {
        TicketsTheme {
            EventDetailContent(event = event, eventTitle = event.name, onSendEvent = {})
        }
    }
}