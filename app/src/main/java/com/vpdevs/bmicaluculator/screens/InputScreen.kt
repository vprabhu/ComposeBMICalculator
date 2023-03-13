package com.vpdevs.bmicaluculator.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vpdevs.bmicaluculator.R
import com.vpdevs.bmicaluculator.data.Person
import com.vpdevs.bmicaluculator.ui.theme.*
import com.vpdevs.bmicaluculator.utils.Utils

@Composable
fun InputScreen(
    onBMICalculationButtonClicked: (Person) -> Unit = {}
) {
    val maleClickableState = remember {
        mutableStateOf(false)
    }
    val femaleClickableState = remember {
        mutableStateOf(false)
    }
    val sliderPosition = remember { mutableStateOf(0f) }
    val weight = remember {
        mutableStateOf(40)
    }
    val age = remember {
        mutableStateOf(18)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Background),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        GenderSelectionUI(maleClickableState, femaleClickableState)
        HeightSelectionUI(sliderPosition)
        WeightSelectionUI(weight, age)
        Spacer(
            modifier = Modifier.padding(
                top = 16.dp
            )
        )
        Button(
            onClick = {
                var gender = ""
                if (maleClickableState.value) {
                    gender = "Male"
                } else {
                    gender = "Female"
                }
                val bmi = calculateBMI(
                    height = (sliderPosition.value) / 100,
                    weight = weight.value.toFloat()
                )
                val color = decideColor(bmi)
                val person = Person(
                    gender = gender,
                    height = sliderPosition.value.toString(),
                    weight = weight.value.toString(),
                    age = age.value.toString(),
                    bmi = bmi.toString(),
                    color = color
                )
                Log.d("InputScreen", "person :  $person")
                onBMICalculationButtonClicked(person)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = DarkRed,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "CALCULATE YOUR BMI",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InputScreenPreview() {
    InputScreen() {

    }
}

@Composable
fun GenderSelectionUI(
    maleClickableState: MutableState<Boolean>,
    femaleClickableState: MutableState<Boolean>
) {
    val maleBgColor = remember {
        mutableStateOf(CardBackground)
    }
    val femaleBgColor = remember {
        mutableStateOf(CardBackground)
    }
    Row(
        modifier = Modifier
            .padding(
                top = 16.dp,
                bottom = 16.dp,
                end = 8.dp
            )
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .height(200.dp)
                .padding(start = 8.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(color = maleBgColor.value)
                    .fillMaxHeight()
                    .weight(0.5f)
                    .clickable {
                        maleClickableState.value = !maleClickableState.value
                        if (maleClickableState.value) {
                            maleBgColor.value = DarkRed
                        } else {
                            maleBgColor.value = CardBackground
                        }
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.male),
                    contentDescription = "Male",
                    tint = Color.White,
                    modifier = Modifier
                        .height(160.dp)
                        .width(160.dp)
                )
                Text(
                    text = "MALE",
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .height(200.dp)
                .padding(start = 8.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(color = femaleBgColor.value)
                    .fillMaxHeight()
                    .weight(0.5f)
                    .clickable {
                        femaleClickableState.value = !femaleClickableState.value
                        if (femaleClickableState.value) {
                            femaleBgColor.value = DarkRed
                        } else {
                            femaleBgColor.value = CardBackground
                        }
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.female),
                    contentDescription = "FeMale",
                    tint = Color.White,
                    modifier = Modifier
                        .height(160.dp)
                        .width(160.dp)
                )
                Text(
                    text = "FEMALE",
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun HeightSelectionUI(sliderPosition: MutableState<Float>) {
    Card(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = CardBackground)
        ) {
            Text(
                text = "HEIGHT",
                color = Color.White,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${sliderPosition.value} cm",
                color = Color.White,
                fontSize = 48.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Slider(
                value = sliderPosition.value,
                onValueChange = {
                    sliderPosition.value = Utils.roundOffDecimal(it)
                },
                valueRange = 0f..250f,
                modifier = Modifier.padding(32.dp),
                colors = SliderDefaults.colors(
                    thumbColor = DarkRed,
                    activeTrackColor = Color.White
                )
            )
        }
    }

}

@Composable
fun WeightSelectionUI(weight: MutableState<Int>, age: MutableState<Int>) {
    Row {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .height(200.dp)
                .padding(start = 8.dp, end = 4.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = CardBackground)
            ) {
                Text(
                    text = "WEIGHT",
                    color = Color.White,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, end = 4.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = weight.value.toString(),
                    color = Color.White,
                    fontSize = 48.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    FloatingActionButton(
                        modifier = Modifier
                            .width(48.dp)
                            .height(48.dp),
                        onClick = {
                            if (weight.value > 40) {
                                weight.value = weight.value - 1
                            }
                        },
                        backgroundColor = DarkRed,
                        contentColor = Color.White
                    ) {
                        Text(text = "-", fontSize = 24.sp)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    FloatingActionButton(
                        modifier = Modifier
                            .width(48.dp)
                            .height(48.dp),
                        onClick = {
                            weight.value = weight.value + 1
                        },
                        backgroundColor = DarkRed,
                        contentColor = Color.White
                    ) {
                        Text(text = "+", fontSize = 24.sp)
                    }
                }
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .height(200.dp)
                .padding(start = 4.dp, end = 8.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = CardBackground)
            ) {
                Text(
                    text = "AGE",
                    color = Color.White,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = age.value.toString(),
                    color = Color.White,
                    fontSize = 48.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    FloatingActionButton(
                        modifier = Modifier
                            .width(48.dp)
                            .height(48.dp),
                        onClick = {
                            if (age.value > 12) {
                                age.value = age.value - 1
                            }
                        },
                        backgroundColor = DarkRed,
                        contentColor = Color.White
                    ) {
                        Text(text = "-", fontSize = 24.sp)
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    FloatingActionButton(
                        modifier = Modifier
                            .width(48.dp)
                            .height(48.dp),
                        onClick = {
                            age.value = age.value + 1
                        },
                        backgroundColor = DarkRed,
                        contentColor = Color.White
                    ) {
                        Text(text = "+", fontSize = 24.sp)
                    }
                }
            }
        }
    }

}

private fun calculateBMI(height: Float, weight: Float): Float {
    val heightSquared = height.times(height)
    return weight.div(heightSquared)
}

private fun decideColor(bmiResult: Float): Color {
    var color = Color.White
    if (bmiResult < 18.5) {
        color = Green
    } else if (bmiResult > 18.5 && bmiResult < 24.9) {
        color = Yellow
    } else if (bmiResult > 25 && bmiResult < 29.9) {
        color = LightRed
    } else if (bmiResult > 30) {
        color = DarkRed
    }
    return color
}
