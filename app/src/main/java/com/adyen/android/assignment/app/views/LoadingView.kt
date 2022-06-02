package com.adyen.android.assignment.app.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoadingView(modifier: Modifier = Modifier, delayMillis: Long = 100L) {
    TimedVisibility(
        delayMillis = delayMillis,
        visibility = false,
        modifier = modifier,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = when (modifier == Modifier) {
                true -> Modifier.fillMaxSize()
                false -> modifier
            }
        ) {
            CircularProgressIndicator(
                modifier.size(48.dp),
                MaterialTheme.colors.secondary,
                ProgressIndicatorDefaults.StrokeWidth
            )

        }
    }

}

@Composable
fun TimedVisibility(
    modifier: Modifier = Modifier,
    delayMillis: Long = 4000,
    visibility: Boolean = true,
    content: @Composable () -> Unit
) {
    var visible by remember { mutableStateOf(visibility) }
    val coroutine = rememberCoroutineScope()

    DisposableEffect(Unit) {
        val job = coroutine.launch {
            delay(delayMillis)
            visible = !visible
        }

        onDispose {
            job.cancel()
        }
    }
    AnimatedVisibility(visible = visible, modifier = modifier, enter = fadeIn(), exit = fadeOut()) {
        content()
    }
}