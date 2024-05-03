package com.bluewhaleyt.flashbar.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bluewhaleyt.flashbar.model.FlashbarMessageType
import com.bluewhaleyt.flashbar.model.FlashbarPosition
import com.bluewhaleyt.flashbar.state.FlashbarState
import com.bluewhaleyt.flashbar.utils.containerColor
import com.bluewhaleyt.flashbar.utils.contentColor
import com.bluewhaleyt.flashbar.utils.rippleColor
import java.util.Timer
import kotlin.concurrent.schedule

@Composable
internal fun Flashbar(
    modifier: Modifier = Modifier,
    state: FlashbarState,
    enterTransition: EnterTransition,
    exitTransition: ExitTransition,
    position: FlashbarPosition,
    horizontalSpacing: Dp,
    maxLines: Int
) {
    DisposableEffect(key1 = state.updated) {
        state.show = true
        val timer = Timer("Animate message bar timer", true)
        timer.schedule(state.duration) {
            state.show = false
        }
        onDispose {
            timer.cancel()
            timer.purge()
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = when (position) {
            FlashbarPosition.Top -> Arrangement.Top
            FlashbarPosition.Bottom -> Arrangement.Bottom
        }
    ) {
        AnimatedVisibility(
            visible = state.show && state.message != null,
            enter = enterTransition,
            exit = exitTransition
        ) {
            Flashbar(
                modifier = modifier,
                state = state
            ) {
                state.message?.let {
                    it.icon?.let {
                        Row {
                            Icon(
                                imageVector = it,
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(horizontalSpacing))
                        }
                    }
                    Text(
                        modifier = Modifier.weight(1f),
                        text = it.text,
                        maxLines = maxLines
                    )
                    it.actions?.let {
                        Row {
                            Spacer(modifier = Modifier.padding(horizontalSpacing - 8.dp))
                            FlashbarActionRow(
                                items = it,
                                contentColor = state.message?.type?.contentColor ?: Color.Unspecified
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
internal fun Flashbar(
    modifier: Modifier = Modifier,
    state: FlashbarState,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(state.message?.type?.containerColor ?: Color.Unspecified)
            .then(modifier)
            .animateContentSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CompositionLocalProvider(
            LocalContentColor provides state.message?.type?.contentColor!!
        ) {
            content()
        }
    }
}