package com.bluewhaleyt.flashbar.state

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import com.bluewhaleyt.flashbar.model.FlashbarAction
import com.bluewhaleyt.flashbar.model.FlashbarMessage
import com.bluewhaleyt.flashbar.model.FlashbarMessageType

class FlashbarState {
    companion object {
        private const val DURATION = 3000L
    }
    internal var show by mutableStateOf(false)
    internal var message by mutableStateOf<FlashbarMessage?>(null)
    internal var updated by mutableStateOf(false)
    internal var duration by mutableLongStateOf(DURATION)

    fun dismiss() {
        show = false
    }

    fun info(
        text: String,
        icon: ImageVector = Icons.Outlined.Info,
        actions: List<FlashbarAction> = emptyList(),
        duration: Long = DURATION
    ) {
        message(text, icon, actions, FlashbarMessageType.Info, duration)
    }

    fun error(
        text: String,
        icon: ImageVector = Icons.Outlined.ErrorOutline,
        actions: List<FlashbarAction> = emptyList(),
        duration: Long = DURATION
    ) {
        message(text, icon, actions, FlashbarMessageType.Error, duration)
    }

    private fun message(
        text: String,
        icon: ImageVector,
        actions: List<FlashbarAction> = emptyList(),
        type: FlashbarMessageType,
        duration: Long
    ) {
        if (text.isNotEmpty()) {
            this.message = FlashbarMessage(
                text = text,
                icon = icon,
                actions = actions,
                type = type
            )
            this.duration = duration
            this.updated = !updated
        }
    }
}

@Composable
fun rememberFlashbarState() = remember {
    FlashbarState()
}