package com.bluewhaleyt.flashbar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var flashbarMessageText by mutableStateOf("")
    var selectedFlashbarPositionIndex by mutableIntStateOf(0)
    var selectedFlashbarMessageTypeIndex by mutableIntStateOf(0)
}