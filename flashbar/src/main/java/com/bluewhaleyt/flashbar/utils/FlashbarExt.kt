package com.bluewhaleyt.flashbar.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.bluewhaleyt.flashbar.model.FlashbarMessage
import com.bluewhaleyt.flashbar.model.FlashbarMessageType
import com.bluewhaleyt.flashbar.model.FlashbarPosition

internal val FlashbarMessageType.containerColor
    @Composable get() = when (this) {
        FlashbarMessageType.Info -> MaterialTheme.colorScheme.primaryContainer
        FlashbarMessageType.Error -> MaterialTheme.colorScheme.errorContainer
    }

internal val FlashbarMessageType.contentColor
    @Composable get() = contentColorFor(backgroundColor = this.containerColor)

internal val FlashbarMessageType.rippleColor
    @Composable get() = when (this) {
        FlashbarMessageType.Info -> MaterialTheme.colorScheme.primary
        FlashbarMessageType.Error -> MaterialTheme.colorScheme.error
    }

internal fun getFlashbarContainerPadding(
    position: FlashbarPosition,
    edgeToEdge: Boolean
): PaddingValues {
    var padding = PaddingValues(0.dp)
    if (edgeToEdge) {
        if (position == FlashbarPosition.Top) {
            padding = PaddingValues(top = 48.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
        } else if (position == FlashbarPosition.Bottom) {
            padding = PaddingValues(top = 16.dp, bottom = 48.dp, start = 16.dp, end = 16.dp)
        }
    } else {
        padding = PaddingValues(16.dp)
    }
    return padding
}