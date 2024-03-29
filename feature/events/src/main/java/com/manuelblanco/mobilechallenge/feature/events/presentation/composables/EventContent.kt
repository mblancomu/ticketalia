package com.manuelblanco.mobilechallenge.feature.events.presentation.composables

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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.manuelblanco.mobilechallenge.core.data.mapper.formattedDate
import com.manuelblanco.mobilechallenge.core.data.mapper.formattedTime
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme
import com.manuelblanco.mobilechallenge.core.designsystem.utils.rateColors
import com.manuelblanco.mobilechallenge.core.domain.model.Event
import com.manuelblanco.mobilechallenge.core.testing.data.eventDetail
import com.manuelblanco.mobilechallenge.core.ui.components.FeatureItemList
import com.manuelblanco.mobilechallenge.core.ui.components.NameItemList
import com.manuelblanco.mobilechallenge.core.ui.components.TicketsPoster
import com.manuelblanco.mobilechallenge.core.ui.components.TicketsPriceShape
import com.manuelblanco.mobilechallenge.core.ui.components.drawTicketPath
import com.manuelblanco.mobilechallenge.feature.events.R
import java.util.concurrent.ThreadLocalRandom

/**
 * Created by Manuel Blanco Murillo on 31/1/24.
 */

@Composable
fun EventContent(
    modifier: Modifier = Modifier,
    event: Event,
    onEventClicked: (id: String, title: String) -> Unit
) {
    Card(
        modifier = modifier.clickable { onEventClicked(event.id, event.name) },
        colors = CardDefaults.cardColors().copy(contentColor = TicketsTheme.colors.outline),
        shape = RoundedCornerShape(size = TicketsTheme.dimensions.paddingSmallMediumDouble),
        elevation = CardDefaults.cardElevation(),
    ) {
        Box {
            TicketsPoster(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                posterPath = event.imageUrl
            )
            EventPrice(
                modifier = Modifier.align(Alignment.TopEnd),
                price = event.price,
                currency = event.currency
            )
            EventInfo(
                event,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .height(TicketsTheme.dimensions.cardInfoHeight)
                    .fillMaxWidth()
                    .background(TicketsTheme.colors.primary.copy(alpha = 0.9f)),
            )
        }
    }
}

@Composable
private fun EventMainInfo(city: String, modifier: Modifier) {
    val colors = Color.rateColors(eventRate = ThreadLocalRandom.current().nextDouble(1.0, 10.0))
    val brush = remember(city) { Brush.horizontalGradient(colors) }
    Text(
        text = city,
        style = TicketsTheme.typography.bodyLarge.copy(color = TicketsTheme.colors.surface),
        modifier = modifier
            .background(brush, RoundedCornerShape(50))
            .padding(horizontal = TicketsTheme.dimensions.paddingMediumLarge)
            .shadow(TicketsTheme.dimensions.paddingMedium)
    )
}

@Composable
private fun EventInfo(event: Event?, modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(TicketsTheme.dimensions.paddingSmall),
        modifier = modifier.padding(
            horizontal = TicketsTheme.dimensions.paddingMedium,
            vertical = TicketsTheme.dimensions.paddingMedium
        ),
    ) {
        NameItemList(name = event?.name.toString())
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Column {
                FeatureItemList(
                    Icons.Default.DateRange,
                    if (!event?.dateTime.isNullOrBlank()) formattedDate(event?.dateTime) else
                        stringResource(id = R.string.no_data)
                )
                FeatureItemList(
                    Icons.Default.AccessTime,
                    if (!event?.dateTime.isNullOrBlank()) formattedTime(event?.dateTime) else
                        stringResource(id = R.string.no_data)
                )
            }

            if (!event?.city.isNullOrBlank()) {
                EventMainInfo(
                    event?.city?.ifEmpty { "" }.toString(),
                    modifier = Modifier
                        .zIndex(2f)
                        .padding(end = TicketsTheme.dimensions.paddingMedium)
                        .offset(y = TicketsTheme.dimensions.paddingSmall),
                )
            }
        }
    }
}

@Composable
fun EventPrice(modifier: Modifier, price: Double, currency: String) {
    val validPrice = if (price != 0.0) price else "--.-"
    Text(
        text = "$validPrice $currency",
        color = TicketsTheme.colors.onSecondary,
        style = TicketsTheme.typography.bodySmall,
        textAlign = TextAlign.Center,
        modifier = modifier
            .wrapContentSize()
            .padding(TicketsTheme.dimensions.paddingMedium)
            .graphicsLayer {
                shadowElevation = 8.dp.toPx()
                shape = TicketsPriceShape(8.dp.toPx())
                clip = true
            }
            .background(color = Color(0xFFbf360c))
            .drawBehind {
                scale(scale = 0.9f) {
                    drawPath(
                        path = drawTicketPath(size = size, cornerRadius = 8.dp.toPx()),
                        color = Color(0xFFD6A728),
                        style = Stroke(
                            width = 2.dp.toPx(),
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
                        )
                    )
                }
            }
            .padding(TicketsTheme.dimensions.paddingSmallMediumDouble)
    )
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Preview
@Composable
fun EventContentComponentPreview(
    event: Event = eventDetail.data
) {
    BoxWithConstraints {
        TicketsTheme {
            EventContent(event = event, onEventClicked = { _, _ -> })
        }
    }
}