package com.bluewhaleyt.flashbar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bluewhaleyt.flashbar.model.FlashbarDefaults

class MainViewModel : ViewModel() {
    var flashbarMessageText by mutableStateOf("")
    var flashbarDuration by mutableFloatStateOf(FlashbarDefaults.DURATION_SHORT.toFloat())
    var selectedFlashbarPositionIndex by mutableIntStateOf(0)
    var selectedFlashbarMessageTypeIndex by mutableIntStateOf(0)
}