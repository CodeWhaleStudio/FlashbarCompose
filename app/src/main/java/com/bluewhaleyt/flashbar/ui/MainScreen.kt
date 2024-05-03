package com.bluewhaleyt.flashbar.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bluewhaleyt.flashbar.model.FlashbarMessageType
import com.bluewhaleyt.flashbar.model.FlashbarPosition
import com.bluewhaleyt.flashbar.MainViewModel
import com.bluewhaleyt.flashbar.model.FlashbarAction
import com.bluewhaleyt.flashbar.state.rememberFlashbarState

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel()
) {
    val flashbarState = rememberFlashbarState()
    val flashbarPosition = FlashbarPosition.entries[viewModel.selectedFlashbarPositionIndex]
    val flashbarMessageType = FlashbarMessageType.entries[viewModel.selectedFlashbarMessageTypeIndex]
    FlashbarContainer(
        state = flashbarState,
        position = flashbarPosition,
        edgeToEdge = true
    ) {
        MainContainer(
            modifier = Modifier.padding(16.dp),
            viewModel = viewModel,
            onSendMessage = {
                when (flashbarMessageType) {
                    FlashbarMessageType.Info -> flashbarState.info(
                        text = viewModel.flashbarMessageText,
                    )
                    FlashbarMessageType.Error -> flashbarState.error(
                        text = viewModel.flashbarMessageText,
                        actions = listOf(
                            FlashbarAction("Dismiss") {
                                flashbarState.dismiss()
                            }
                        )
                    )
                }
            }
        ) {
            Text(text = "made by BlueWhaleYT")
        }
    }
}