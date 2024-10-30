package jp.speakbuddy.edisonandroidexercise.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun EdisonAndroidExerciseTheme(
    content: @Composable () -> Unit
) {
    val dimensions: Dimensions = LocalDimensions.current
    val colors: ColorEnsemble = LocalColors.current
    val edisonTypography: EdisonTypography = LocalTypography.current

    CompositionLocalProvider(
        LocalDimensions provides dimensions,
        LocalColors provides colors,
        LocalTypography provides edisonTypography
    ) {
        val materialsTypo = androidx.compose.material3.Typography(
            bodyMedium = edisonTypography.normal,
            titleLarge = edisonTypography.largeAndBold
        )

        MaterialTheme(
            typography = materialsTypo
        ) {
            content()
        }
    }
}

object EdisonTheme {
    val dimensions: Dimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalDimensions.current

    val colors: ColorEnsemble
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: EdisonTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}