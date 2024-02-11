package com.manuelblanco.mobilechallenge.feature.events.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.manuelblanco.mobilechallenge.core.designsystem.component.TicketsOutlinedButton
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme
import com.manuelblanco.mobilechallenge.core.ui.components.TicketsPoster
import com.manuelblanco.mobilechallenge.core.ui.components.TicketsTopBar
import com.manuelblanco.mobilechallenge.core.ui.mvi.SIDE_EFFECTS_KEY
import com.manuelblanco.mobilechallenge.feature.events.R
import com.manuelblanco.mobilechallenge.feature.events.presentation.EventDetailContract
import com.manuelblanco.mobilechallenge.feature.events.presentation.EventDetailViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

/**
 * Created by Manuel Blanco Murillo on 7/2/24.
 */

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EventDetailScreen(
    eventId: String,
    eventTitle: String,
    onNavigationRequested: (navigationEffect: EventDetailContract.Effect.Navigation) -> Unit,
    eventDetailViewModel: EventDetailViewModel = hiltViewModel()
) {
    val state by eventDetailViewModel.viewState.collectAsStateWithLifecycle()
    val event = state.event
    val effect = eventDetailViewModel.effect

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effect.onEach { effect ->
            when (effect) {
                is EventDetailContract.Effect.Navigation.Back -> {
                    onNavigationRequested(effect)
                }
            }
        }.collect()
    }

    Scaffold(
        topBar = {
            TicketsTopBar(
                isCentered = false,
                isNavigable = true,
                containerColor = Color.Transparent,
                onBack = { eventDetailViewModel.setEvent(EventDetailContract.Event.GoBack) })
        }
    ) {
        when {
            state.isError -> {}
            state.isLoading -> {
                Progress()
            }

            state.isInit -> {
                eventDetailViewModel.getEvent(eventId)
            }

            else -> {
                Box(
                    modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
                ) {
                    TicketsPoster(
                        posterPath = event?.imageUrl ?: "", modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.TopCenter)
                            .height(TicketsTheme.dimensions.posterDetailHeight)
                    )
                    Card(
                        modifier = Modifier
                            .padding(top = TicketsTheme.dimensions.cardDetailPadding)
                            .align(Alignment.BottomCenter)
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
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        top = TicketsTheme.dimensions.paddingMedium,
                                        end = TicketsTheme.dimensions.paddingMedium,
                                        start = TicketsTheme.dimensions.paddingMedium,
                                        bottom = TicketsTheme.dimensions.paddingMediumDouble,
                                    ),
                                text = eventTitle,
                                textAlign = TextAlign.Center,
                                style = TicketsTheme.typography.headlineSmall,
                                color = TicketsTheme.colors.background,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 2
                            )
                            Row {

                            }
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                TicketsOutlinedButton(
                                    modifier = Modifier
                                        .padding(end = TicketsTheme.dimensions.paddingMedium)
                                        .width(TicketsTheme.dimensions.buttonDetailWidth),
                                    onClick = {},
                                    label = stringResource(id = R.string.button_tickets),
                                    enabled = true,
                                )
                                TicketsOutlinedButton(
                                    modifier = Modifier
                                        .padding(start = TicketsTheme.dimensions.paddingMedium)
                                        .width(TicketsTheme.dimensions.buttonDetailWidth),
                                    onClick = {

                                    },
                                    label = stringResource(id = R.string.button_location),
                                    enabled = true,
                                )
                            }
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        top = TicketsTheme.dimensions.paddingMediumDouble,
                                        bottom = TicketsTheme.dimensions.paddingMedium,
                                    ),
                                text = if (event?.description.isNullOrBlank()) stringResource(id = R.string.fake_description) else event?.description
                                    ?: stringResource(id = R.string.fake_description),
                                textAlign = TextAlign.Justify,
                                style = TicketsTheme.typography.bodyMedium,
                                color = TicketsTheme.colors.secondaryContainer
                            )
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun Progress() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}