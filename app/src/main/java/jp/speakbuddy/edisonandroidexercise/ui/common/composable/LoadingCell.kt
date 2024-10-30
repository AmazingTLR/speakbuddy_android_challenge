package jp.speakbuddy.edisonandroidexercise.ui.common.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import jp.speakbuddy.edisonandroidexercise.ui.theme.EdisonTheme

@Composable
fun LoadingCell(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(EdisonTheme.dimensions.L),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}