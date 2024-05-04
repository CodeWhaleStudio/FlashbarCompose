package com.bluewhaleyt.flashbar.model

import androidx.compose.ui.graphics.vector.ImageVector

data class FlashbarMessage internal constructor(
    val text: String,
    val icon: ImageVector?,
    val actions: List<FlashbarAction>?,
    val duration: Long,
    val type: FlashbarMessageType
)