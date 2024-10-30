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
            default = EdisonColor.Neutral100,
            accent = EdisonColor.Neutral150
        ),
        text = ColorComposition(
            default = EdisonColor.Neutral200,
            accent = EdisonColor.Neutral200
        )
    )
}

object EdisonColor {
    val Neutral100 = Color(0xFFCCCCCC)
    val Neutral150 = Color(0xFF444444)
    val Neutral200 = Color(0xFF000000)


    val Teal = Color(0xFF89E3DD)
    val Orange = Color(0xFFE85E38)
    val Acid = Color(0xFFE5E857)
    val Green = Color(0xFF43A047)
    val Carrot = Color(0xFFDA7726)
    val Red = Color(0xFFE53935)
    val Lava = Color(0xFFEF3841)
    val Flame = Color(0xFFFFCE52)
    val Pink = Color(0xFFE64796)
    val Magenta = Color(0xFFD449D4)
    val Indigo = Color(0xFF6849FF)
    val Blue = Color(0xFF4773EB)
    val Jellyfish = Color(0xFF30E0C6)
    val Algae = Color(0xFF74E788)
}