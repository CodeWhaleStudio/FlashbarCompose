package com.bluewhaleyt.flashbar.ui

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bluewhaleyt.flashbar.model.FlashbarPosition
import com.bluewhaleyt.flashbar.state.FlashbarState
import com.bluewhaleyt.flashbar.utils.getFlashbarContainerPadding

@Composable
fun FlashbarContainer(
    modifier: Modifier = Modifier,
    state: FlashbarState,
    enterTransition: EnterTransition = fadeIn() + expandVertically(),
    exitTransition: ExitTransition = fadeOut() + shrinkVertically(),
    position: FlashbarPosition = FlashbarPosition.Top,
    horizontalSpacing: Dp = 16.dp,
    maxLines: Int = 2,
    textStyle: TextStyle = LocalTextStyle.current,
    edgeToEdge: Boolean = false,
    padding: PaddingValues = getFlashbarContainerPadding(position, edgeToEdge),
    content: @Composable ColumnScope.() -> Unit
) {
    FlashbarContainer(
        modifier = modifier,
        innerModifier = Modifier.padding(padding),
        state = state,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        position = position,
        horizontalSpacing = horizontalSpacing,
        maxLines = maxLines,
        textStyle = textStyle
    ) {
        Column(
            modifier = Modifier
                .then(
                    if (edgeToEdge) {
                        Modifier
                            .statusBarsPadding()
                            .navigationBarsPadding()
                    } else Modifier
                )
        ) {
            content()
        }
    }
}

@Composable
private fun FlashbarContainer(
    modifier: Modifier = Modifier,
    innerModifier: Modifier = Modifier,
    state: FlashbarState,
    enterTransition: EnterTransition,
    exitTransition: ExitTransition,
    position: FlashbarPosition,
    horizontalSpacing: Dp,
    maxLines: Int,
    textStyle: TextStyle,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        content()
        Flashbar(
            modifier = innerModifier,
            state = state,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            position = position,
            horizontalSpacing = horizontalSpacing,
            maxLines = maxLines,
            textStyle = textStyle
        )
    }
}