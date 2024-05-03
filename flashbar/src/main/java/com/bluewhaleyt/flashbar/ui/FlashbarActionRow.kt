package com.bluewhaleyt.flashbar.ui

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.bluewhaleyt.flashbar.model.FlashbarAction

@Composable
internal fun FlashbarActionRow(
    modifier: Modifier = Modifier,
    items: List<FlashbarAction>,
    contentColor: Color,
) {
    LazyRow(modifier) {
        itemsIndexed(
            items = items,
            key = { i, _ -> i }
        ) { index, item ->
            TextButton(
                onClick = item.onClick,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = contentColor
                )
            ) {
                Text(text = item.label)
            }
        }
    }
}