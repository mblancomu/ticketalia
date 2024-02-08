package com.manuelblanco.mobilechallenge.feature.events.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.manuelblanco.mobilechallenge.core.common.utils.formattedDate
import com.manuelblanco.mobilechallenge.core.common.utils.formattedTime
import com.manuelblanco.mobilechallenge.core.designsystem.utils.rateColors
import com.manuelblanco.mobilechallenge.core.model.data.Event

/**
 * Created by Manuel Blanco Murillo on 31/1/24.
 */

@Composable
fun EventContent(
    event: Event,
    modifier: Modifier = Modifier,
    onEventClicked: (id: String, title: String) -> Unit
) {
    Card(
        modifier = modifier.clickable { onEventClicked(event.id, event.name) },
        shape = RoundedCornerShape(size = 8.dp),
        elevation = CardDefaults.cardElevation(),
    ) {
        Box {
            EventPoster(event.imageUrl, event.name)
            EventInfo(
                event,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .height(72.dp)
                    .fillMaxWidth()
                    .background(Color(0x97000000)),
            )
        }
    }
}

@Composable
private fun BoxScope.EventPoster(posterPath: String, eventName: String) {
    val painter = rememberAsyncImagePainter(
        model = posterPath,
        error = rememberVectorPainter(Icons.Filled.BrokenImage),
        placeholder = rememberVectorPainter(Icons.Default.Movie),
    )
    val colorFilter = when (painter.state) {
        is AsyncImagePainter.State.Loading, is AsyncImagePainter.State.Error -> ColorFilter.tint(
            MaterialTheme.colorScheme.background
        )

        else -> null
    }
    val scale =
        if (painter.state !is AsyncImagePainter.State.Success) ContentScale.Crop else ContentScale.FillBounds

    Image(
        painter = painter,
        colorFilter = colorFilter,
        contentDescription = "Image for Event",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
            .align(Alignment.Center),
    )
}

@Composable
private fun EventRate(city: String, modifier: Modifier) {
    val colors = Color.rateColors(eventRate = 10.0)
    val brush = remember(city) { Brush.horizontalGradient(colors) }
    Text(
        text = city,
        style = MaterialTheme.typography.bodyLarge.copy(color = Color.White),
        modifier = modifier
            .background(brush, RoundedCornerShape(50))
            .padding(horizontal = 10.dp)
            .shadow(8.dp)
    )
}

@Composable
private fun EventInfo(event: Event, modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier.padding(horizontal = 8.dp, vertical = 8.dp),
    ) {
        EventName(name = event.name)
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Column {
                EventFeature(
                    Icons.Default.DateRange,
                    if (event.dateTime.isNotEmpty()) formattedDate(event.dateTime) else ""
                )
                EventFeature(
                    Icons.Default.AccessTime,
                    if (event.dateTime.isNotEmpty()) formattedTime(event.dateTime) else ""
                )
            }

            if (event.city.isNotEmpty()) {
                EventRate(
                    event.city.ifEmpty { "" },
                    modifier = Modifier
                        .zIndex(2f)
                        .padding(end = 8.dp)
                        .offset(y = 4.dp),
                )
            }
        }
    }
}

@Composable
private fun EventName(name: String) = Text(
    text = name,
    style = MaterialTheme.typography.bodyMedium.copy(
        color = Color.White,
        letterSpacing = 1.5.sp,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.W500,
    ),
    maxLines = 1,
    overflow = TextOverflow.Ellipsis,
)

@Composable
private fun EventFeature(icon: ImageVector, field: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = field,
            style = MaterialTheme.typography.bodySmall.copy(
                color = Color.White,
                letterSpacing = 1.5.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W400,
            ),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier.padding(horizontal = 2.dp),
        )
    }
}