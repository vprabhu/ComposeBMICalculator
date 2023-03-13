package com.vpdevs.bmicaluculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.vpdevs.bmicaluculator.ui.theme.BMICalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMICalculatorTheme {
                // A surface container using the 'background' color from the theme
               BMIScreen()
            }
        }
    }
}