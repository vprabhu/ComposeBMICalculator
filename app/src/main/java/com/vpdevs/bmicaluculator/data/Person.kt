package com.vpdevs.bmicaluculator.data

import androidx.compose.ui.graphics.Color
import com.vpdevs.bmicaluculator.ui.theme.Green

data class Person(
    val gender: String = "",
    val height: String = "",
    val weight: String = "",
    val age: String = "",
    val bmi: String = "",
    val color: Color = Color.White
)
