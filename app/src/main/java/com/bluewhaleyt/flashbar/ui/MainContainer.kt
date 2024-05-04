package com.bluewhaleyt.flashbar.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bluewhaleyt.flashbar.MainViewModel
import com.bluewhaleyt.flashbar.model.FlashbarDefaults
import com.bluewhaleyt.flashbar.model.FlashbarMessageType
import com.bluewhaleyt.flashbar.model.FlashbarPosition

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
        Section(text = "Message") {
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
        }
        Section(text = "Position") {
            SingleOptionRow(
                items = FlashbarPosition.entries,
                selected = { viewModel.selectedFlashbarPositionIndex == it },
                onItemSelected = { viewModel.selectedFlashbarPositionIndex = it },
                itemContent = {
                    Text(text = it.name)
                }
            )
        }
        Section(text = "Type") {
            SingleOptionRow(
                items = FlashbarMessageType.entries,
                selected = { viewModel.selectedFlashbarMessageTypeIndex == it },
                onItemSelected = { viewModel.selectedFlashbarMessageTypeIndex = it },
                itemContent = {
                    Text(text = it.name)
                }
            )
        }
        Section(text = "Duration") {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Slider(
                    modifier = Modifier.weight(1f),
                    value = viewModel.flashbarDuration,
                    onValueChange = {
                        viewModel.flashbarDuration = it
                    },
                    valueRange = FlashbarDefaults.DURATION_SHORT.toFloat()..15000f
                )
                Text(text = String.format("%.0f", viewModel.flashbarDuration))
            }
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

@Composable
private fun Section(
    modifier: Modifier = Modifier,
    text: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier.padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        content()
    }
}