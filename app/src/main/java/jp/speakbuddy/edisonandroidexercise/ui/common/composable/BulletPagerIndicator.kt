package jp.speakbuddy.edisonandroidexercise.ui.common.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import jp.speakbuddy.edisonandroidexercise.ui.theme.EdisonTheme

@Composable
fun BulletPagerIndicator(
    numberOfPagerBullets: Int,
    currentPage: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(bottom = EdisonTheme.dimensions.XL),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(numberOfPagerBullets) { iteration ->
            val neutralColor = EdisonTheme.colors.background.default
            val selectedColor = EdisonTheme.colors.background.accent
            var currentColorOfBullet by remember { mutableStateOf(neutralColor) }

            LaunchedEffect(currentPage) {
                // We only display 4 bullets at a time
                // Mod the current page by 4 to get the position of the current page in the iteration
                val currentPageIsInIteration = currentPage.mod(numberOfPagerBullets) == iteration
                currentColorOfBullet = if (currentPageIsInIteration) selectedColor else neutralColor
            }

            Box(
                modifier = Modifier
                    .padding(EdisonTheme.dimensions.XS)
                    .clip(CircleShape)
                    .background(currentColorOfBullet)
                    .size(EdisonTheme.dimensions.L)
            )
        }
    }
}