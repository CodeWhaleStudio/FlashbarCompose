package com.bluewhaleyt.flashbar.model

data class FlashbarAction(
    val label: String,
    val onClick: () -> Unit
)