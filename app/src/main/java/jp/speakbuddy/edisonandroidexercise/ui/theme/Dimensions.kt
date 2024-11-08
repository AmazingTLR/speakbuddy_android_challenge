package jp.speakbuddy.edisonandroidexercise.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val XXS: Dp = 0.5.dp,
    val XS: Dp = 2.dp,
    val S: Dp = 4.dp,
    val M: Dp = 8.dp,
    val L: Dp = 16.dp,
    val XL: Dp = 32.dp,
    val XXL: Dp = 64.dp,
)

val LocalDimensions = staticCompositionLocalOf { Dimensions() }