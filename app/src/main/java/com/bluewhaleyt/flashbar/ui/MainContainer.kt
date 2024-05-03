package com.bluewhaleyt.flashbar.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bluewhaleyt.flashbar.model.FlashbarMessageType
import com.bluewhaleyt.flashbar.model.FlashbarPosition
import com.bluewhaleyt.flashbar.MainViewModel

@Composable
fun MainContainer(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    onSendMessage: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewModel.flashbarMessageText,
            onValueChange = {
                viewModel.flashbarMessageText = it
            },
            label = {
                Text(text = "Message")
            },
            trailingIcon = {
                AnimatedVisibility(visible = viewModel.flashbarMessageText.isNotEmpty()) {
                    IconButton(onClick = onSendMessage) {
                        Icon(imageVector = Icons.AutoMirrored.Outlined.Send, contentDescription = "Send")
                    }
                }
            }
        )
        Row {
            SingleOptionRow(
                modifier = Modifier.weight(1f),
                items = FlashbarPosition.entries,
                selected = { viewModel.selectedFlashbarPositionIndex == it },
                onItemSelected = { viewModel.selectedFlashbarPositionIndex = it },
                itemContent = {
                    Text(text = it.name)
                }
            )
            SingleOptionRow(
                modifier = Modifier.weight(1f),
                items = FlashbarMessageType.entries,
                selected = { viewModel.selectedFlashbarMessageTypeIndex == it },
                onItemSelected = { viewModel.selectedFlashbarMessageTypeIndex = it },
                itemContent = {
                    Text(text = it.name)
                }
            )
        }
        content()
    }
}

@Composable
private fun <T> SingleOptionRow(
    modifier: Modifier = Modifier,
    items: List<T>,
    selected: (Int) -> Boolean,
    onItemSelected: (Int) -> Unit,
    itemContent: @Composable (T) -> Unit
) {
    SingleChoiceSegmentedButtonRow(modifier) {
        items.forEachIndexed { index, item ->
            SegmentedButton(
                selected = selected(index),
                onClick = {
                    onItemSelected(index)
                },
                shape = SegmentedButtonDefaults.itemShape(index = index, count = items.size),
                label = {
                    itemContent(item)
                }
            )
        }
    }
}