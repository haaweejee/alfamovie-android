package id.haaweejee.test.alfagift.alfamovie.presentation.util

import kotlin.math.roundToInt

fun Double.simplifyNumber(): Double = (this * 10.0).roundToInt() / 10.0

fun Int.detailedDuration(): String = if (this < 60) {
    "$this minutes"
} else {
    val hours = this / 60
    val minutes = this % 60
    "$hours hours $minutes minutes"
}
