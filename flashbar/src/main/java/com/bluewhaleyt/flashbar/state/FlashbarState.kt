package com.bluewhaleyt.flashbar.state

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import com.bluewhaleyt.flashbar.model.FlashbarAction
import com.bluewhaleyt.flashbar.model.FlashbarDefaults
import com.bluewhaleyt.flashbar.model.FlashbarMessage
import com.bluewhaleyt.flashbar.model.FlashbarMessageType

class FlashbarState {
    internal var show by mutableStateOf(false)
    internal var message by mutableStateOf<FlashbarMessage?>(null)
    internal var updated by mutableStateOf(false)

    fun dismiss() {
        show = false
    }

    fun message(
        text: String,
        icon: ImageVector = Icons.Outlined.Info,
        actions: List<FlashbarAction>? = null,
        duration: Long = FlashbarDefaults.DURATION_SHORT
    ) {
        message(FlashbarMessageType.Normal, text, icon, actions, duration)
    }

    fun info(
        text: String,
        icon: ImageVector = Icons.Outlined.Info,
        actions: List<FlashbarAction>? = null,
        duration: Long = FlashbarDefaults.DURATION_SHORT
    ) {
        message(FlashbarMessageType.Info, text, icon, actions, duration)
    }

    fun error(
        text: String,
        icon: ImageVector = Icons.Outlined.ErrorOutline,
        actions: List<FlashbarAction>? = null,
        duration: Long = FlashbarDefaults.DURATION_SHORT
    ) {
        message(FlashbarMessageType.Error, text, icon, actions, duration)
    }

    private fun message(
        type: FlashbarMessageType,
        text: String,
        icon: ImageVector,
        actions: List<FlashbarAction>?,
        duration: Long,
    ) {
        if (text.isNotEmpty()) {
            this.message = FlashbarMessage(
                text = text,
                icon = icon,
                actions = actions,
                duration = duration,
                type = type
            )
            this.updated = !updated
        }
    }
}

@Composable
fun rememberFlashbarState() = remember {
    FlashbarState()
}