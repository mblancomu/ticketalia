package com.manuelblanco.mobilechallenge.feature.venues.composables

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
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Place
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.manuelblanco.mobilechallenge.core.designsystem.utils.distanceColors
import com.manuelblanco.mobilechallenge.core.designsystem.utils.rateColors
import com.manuelblanco.mobilechallenge.core.model.data.Venue
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
            .height(240.dp)
            .padding(top = 0.dp, start = 8.dp, end = 8.dp, bottom = 0.dp)
            .clickable { onVenueClicked(venue.id) },
        shape = RoundedCornerShape(size = 8.dp),
        elevation = CardDefaults.cardElevation(),
    ) {
        Box {
            VenuePoster(posterPath = venue.imageUrl)
            VenueInfo(venue = venue,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .height(72.dp)
                    .fillMaxWidth()
                    .background(Color(0x97000000)))
        }
    }
}

@Composable
private fun BoxScope.VenuePoster(posterPath: String) {
    val painter = rememberAsyncImagePainter(
        model = posterPath,
        error = painterResource(id = R.drawable.venue),
        placeholder = painterResource(id = R.drawable.venue),
    )
    val colorFilter = when (painter.state) {
        is AsyncImagePainter.State.Loading, is AsyncImagePainter.State.Error -> ColorFilter.tint(
            MaterialTheme.colorScheme.background
        )

        else -> null
    }

    Image(
        painter = painter,
        contentDescription = "Image for Event",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
            .align(Alignment.Center),
    )
}

@Composable
private fun VenueInfo(venue: Venue, modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier.padding(horizontal = 8.dp, vertical = 8.dp),
    ) {
        VenueName(name = venue.name)
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Column {
                VenueFeature(
                    Icons.Default.Place,
                    venue.address.plus(venue.city)
                )
                VenueFeature(
                    Icons.Default.Flag,
                    venue.country
                )
            }

            /*if (venue.distance.isNotEmpty() || venue.distance != "0.0") {
                VenueDistance(
                    distance = venue.distance.toDouble(), modifier = Modifier
                        .zIndex(2f)
                        .padding(end = 8.dp)
                        .offset(y = 4.dp)
                )
            }*/
        }
    }
}

@Composable
private fun VenueName(name: String) = Text(
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
private fun VenueFeature(icon: ImageVector, field: String) {
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

@Composable
private fun VenueDistance(distance: Double, modifier: Modifier) {
    val colors = Color.distanceColors(venueDistance = distance)
    val brush = remember(distance) { Brush.horizontalGradient(colors) }
    Text(
        text = distance.toString(),
        style = MaterialTheme.typography.bodyLarge.copy(color = Color.White),
        modifier = modifier
            .background(brush, RoundedCornerShape(50))
            .padding(horizontal = 10.dp)
            .shadow(8.dp)
    )
}