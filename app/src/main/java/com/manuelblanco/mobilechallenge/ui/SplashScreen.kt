package com.manuelblanco.mobilechallenge.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.manuelblanco.mobilechallenge.MainActivity.Companion.SplashWaitTime
import com.manuelblanco.mobilechallenge.R
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme
import kotlinx.coroutines.delay

/**
 * Created by Manuel Blanco Murillo on 29/6/23.
 */

@Composable
fun SplashScreen(modifier: Modifier = Modifier, onTimeout: () -> Unit) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        val currentOnTimeout by rememberUpdatedState(onTimeout)

        LaunchedEffect(true) {
            delay(SplashWaitTime)
            currentOnTimeout()
        }

        val isPlaying by remember { mutableStateOf(true) }
        val speed by remember { mutableFloatStateOf(3.2f) }
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash))
        val lottieAnimatable = rememberLottieAnimatable()
        val progress by animateLottieCompositionAsState(
            composition,
            iterations = LottieConstants.IterateForever,
            isPlaying = isPlaying,
            speed = speed,
            restartOnPlay = false
        )

        LaunchedEffect(composition) {
            lottieAnimatable.animate(
                composition = composition,
                initialProgress = 0f
            )
        }

        LottieAnimation(
            composition,
            progress = { progress },
            modifier = Modifier.size(TicketsTheme.dimensions.splashAnimSize)
        )
    }
}