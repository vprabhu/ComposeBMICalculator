package com.vpdevs.bmicaluculator.utils

import java.math.RoundingMode
import java.text.DecimalFormat

object Utils {

    fun roundOffDecimal(number: Float): Float {
        val df = DecimalFormat("#")
        df.roundingMode = RoundingMode.FLOOR
        return df.format(number).toFloat()
    }
}