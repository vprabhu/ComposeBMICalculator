package com.vpdevs.bmicaluculator.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vpdevs.bmicaluculator.data.Person
import com.vpdevs.bmicaluculator.ui.theme.*
import com.vpdevs.bmicaluculator.utils.Utils
import java.math.RoundingMode
import java.text.DecimalFormat


@Composable
fun OutputScreen(person: Person) {
    val radius = 200f
    val animateFloat = remember {
        Animatable(0f)
    }
    LaunchedEffect(animateFloat) {
        animateFloat.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1500, easing = LinearEasing)
        )
    }
    Column(
        modifier = Modifier
            .padding(top = 16.dp)
            .background(color = Background)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "BMI - ${Utils.roundOffDecimal(person.bmi.toFloat())}",
                color = Color.White,
                fontSize = 48.sp,
                fontFamily = FontFamily.Serif
            )
            androidx.compose.foundation.Canvas(
                modifier = Modifier
                    .wrapContentHeight()
                    .wrapContentWidth()
                    .padding(top = 32.dp)
            ) {
                drawCircle(
                    color = person.color,
                    /*startAngle = 0f,
                    sweepAngle = 360f * animateFloat.value,
                    useCenter = false,
                    topLeft = Offset(size.width / 10, size.height / 10),
                    size = Size(
                        width = radius * 3,
                        height = radius * 3
                    ),*/
                    radius = radius * 2,
                    style = Stroke(25f)
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        showDivider()
        InfoText(text = "Less than 18.5 - UnderWeight", color = Green)
        showDivider()
        InfoText(text = "Between than 18.5 and 24.9 - Normal", color = Yellow)
        showDivider()
        InfoText(text = "Between than 25 and 29.9 - OverWeight", color = LightRed)
        showDivider()
        InfoText(text = "Over 30 - Obese", color = DarkRed)
        showDivider()
    }

}

@Composable
fun showDivider() {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), color = Color.White
    )
}

@Composable
fun InfoText(text: String, color: Color) {
    Text(
        text = text,
        color = color,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
    )
}
