package com.vpdevs.bmicaluculator

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vpdevs.bmicaluculator.screens.InputScreen
import com.vpdevs.bmicaluculator.screens.OutputScreen
import com.vpdevs.bmicaluculator.ui.theme.Background


enum class BMIScreenName {
    InputScreen,
    OutputScreen
}

@Composable
fun BMIScreen(viewModel: BMIViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val navController = rememberNavController()
    Scaffold()
    {
        val uiState = viewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = BMIScreenName.InputScreen.name,
            modifier = Modifier.padding(it)
        ) {
            composable(
                route = BMIScreenName.InputScreen.name
            ) {
                InputScreen(
                    onBMICalculationButtonClicked = { person ->
                        viewModel.setPerson(person)
                        navController.navigate(BMIScreenName.OutputScreen.name)
                    }
                )
            }

            composable(
                route = BMIScreenName.OutputScreen.name
            ) {
                OutputScreen(uiState.value)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BMIScreenPreview() {
    BMIScreen()
}