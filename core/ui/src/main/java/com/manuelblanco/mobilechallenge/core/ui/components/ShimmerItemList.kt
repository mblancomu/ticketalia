package com.manuelblanco.mobilechallenge.core.ui.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme

/**
 * Created by Manuel Blanco Murillo on 19/2/24.
 */

enum class ItemType {
    EVENT, VENUE
}

@Composable
fun ShimmerEffectList(type: ItemType, isVisible: Boolean = true) {
    LazyColumn(
        modifier = Modifier
            .semantics { contentDescription = "Shimmer effect" }
            .fillMaxSize(),
        contentPadding = PaddingValues(
            start = TicketsTheme.dimensions.paddingMedium,
            end = TicketsTheme.dimensions.paddingMedium,
            top = TicketsTheme.dimensions.paddingMedium,
            bottom = TicketsTheme.dimensions.paddingMedium,
        ),
        verticalArrangement = Arrangement.spacedBy(
            TicketsTheme.dimensions.paddingMedium,
            Alignment.CenterVertically
        ),
    ) {
        if (isVisible){
            items(10) {
                ShimmerItemList(type = type)
            }
        }
    }
}

@Composable
fun ShimmerItemList(modifier: Modifier = Modifier, type: ItemType) {
    Card(
        modifier = modifier.height(TicketsTheme.dimensions.cardListHeight),
        shape = RoundedCornerShape(size = TicketsTheme.dimensions.paddingSmallMediumDouble),
        elevation = CardDefaults.cardElevation(),
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(160.dp)
                    .fillMaxWidth()
                    .shimmerEffect()
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(TicketsTheme.dimensions.paddingSmall),
                modifier = modifier.padding(
                    horizontal = TicketsTheme.dimensions.paddingMedium,
                    vertical = TicketsTheme.dimensions.paddingMedium
                ),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(16.dp)
                        .shimmerEffect()
                )
                Spacer(Modifier.height(4.dp))
                Row {
                    Column {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.4f)
                                .height(16.dp)
                                .shimmerEffect()
                        )
                        Spacer(Modifier.height(4.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.4f)
                                .height(16.dp)
                                .shimmerEffect()
                        )
                    }
                    if (type == ItemType.EVENT) {
                        Box(
                            modifier = Modifier
                                .padding(start = 32.dp, top = 12.dp)
                                .zIndex(2f)
                                .height(16.dp)
                                .fillMaxWidth(0.9f)
                                .shimmerEffect()
                        )
                    }
                }
            }
        }
    }
}

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition(label = "")
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        ), label = ""
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFB8B5B5),
                Color(0xFF8F8B8B),
                Color(0xFFB8B5B5),
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}

@Preview
@Composable
fun ShimmerAnimationComponentPreview() {
    TicketsTheme {
        ShimmerItemList(type = ItemType.EVENT)
    }
}