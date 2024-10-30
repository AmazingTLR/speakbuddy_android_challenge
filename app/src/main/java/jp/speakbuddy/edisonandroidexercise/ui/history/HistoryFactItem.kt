package jp.speakbuddy.edisonandroidexercise.ui.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import jp.speakbuddy.edisonandroidexercise.ui.theme.EdisonTheme

@Composable
fun HistoryFactItem(factText: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(EdisonTheme.dimensions.M),
    ) {
        Text(
            text = factText,
            style = EdisonTheme.typography.normal,
            modifier = modifier.padding(EdisonTheme.dimensions.L)
        )
        HorizontalDivider(thickness = EdisonTheme.dimensions.XXS)
    }
}

@Composable
@Preview
fun HistoryFactItemPreview() {
    HistoryFactItem(factText = "Fact 1")
}