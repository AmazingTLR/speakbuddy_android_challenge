package jp.speakbuddy.edisonandroidexercise.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class ColorEnsemble(
    val background: ColorComposition,
    val text: ColorComposition
)

data class ColorComposition(
    val default: Color,
    val accent: Color
)

val LocalColors = staticCompositionLocalOf {
    ColorEnsemble(
        background = ColorComposition(
            default = EdisonColor.Neutral000,
            accent = EdisonColor.Neutral200
        ),
        text = ColorComposition(
            default = EdisonColor.Neutral200,
            accent = EdisonColor.Neutral200
        )
    )
}

object EdisonColor {
    val Neutral000 = Color(0xFFFFFFFF)
    val Neutral200 = Color(0xFF3A393D)


    val color_background_1 = Color(0xFFD0ECFC)
    val color_background_2 = Color(0xFFFBF0DC)
    val color_background_3 = Color(0xFFF9CFCF)
    val color_background_4 = Color(0xFFDCF9E4)
    val color_background_5 = Color(0xFFF9D1B7)
    val color_background_6 = Color(0xFFC9F2F7)
    val color_background_7 = Color(0xFFF2C6DF)
    val color_background_8 = Color(0xFFDBCDF0)
    val color_background_9 = Color(0xFFB6D2FF)
    val color_background_10 = Color(0xFFFBFAC8)
    val color_background_11 = Color(0xFFF4D8B0)
    val color_background_12 = Color(0xFFE8B4B1)
    val color_background_13 = Color(0xFFD6EADF)
    val color_background_14 = Color(0xFFB8E0D2)
}