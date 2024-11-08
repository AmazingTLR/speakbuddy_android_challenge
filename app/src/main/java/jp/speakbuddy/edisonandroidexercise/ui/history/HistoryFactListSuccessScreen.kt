package jp.speakbuddy.edisonandroidexercise.ui.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import jp.speakbuddy.edisonandroidexercise.R
import jp.speakbuddy.edisonandroidexercise.model.FactUI
import jp.speakbuddy.edisonandroidexercise.ui.theme.EdisonTheme

@Composable
fun HistoryFactListSuccessScreen(
    factList: List<FactUI>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.systemBarsPadding()) {
        Text(
            text = stringResource(id = R.string.history_title),
            style = EdisonTheme.typography.largeAndBold,
            modifier = modifier.padding(EdisonTheme.dimensions.L)
        )

        LazyColumn {
            items(factList) { fact ->
                HistoryFactItem(fact.fact, modifier)
            }
        }
    }
}