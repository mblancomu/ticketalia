package com.manuelblanco.mobilechallenge.core.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme
import com.manuelblanco.mobilechallenge.core.ui.R

/**
 * Created by Manuel Blanco Murillo on 31/1/24.
 */

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun Progress(modifier: Modifier = Modifier) {
    BoxWithConstraints(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.progress))
        val lottieAnimatable = rememberLottieAnimatable()

        LaunchedEffect(composition) {
            lottieAnimatable.animate(
                composition = composition,
                initialProgress = 0f
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(TicketsTheme.dimensions.progressBoxCorners))
                .height(TicketsTheme.dimensions.progressBoxHeight)
                .width(TicketsTheme.dimensions.progressBoxWidth)
                .background(color = TicketsTheme.colors.surface.copy(alpha = 0.9f))
        ) {
            LottieAnimation(
                composition,
                modifier = Modifier.padding(top = TicketsTheme.dimensions.paddingMediumLarge),
                alignment = Alignment.Center,
                contentScale = ContentScale.None
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressComponentPreview() {
    TicketsTheme {
        Progress()
    }
}