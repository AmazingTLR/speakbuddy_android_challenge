package jp.speakbuddy.edisonandroidexercise.ui.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.speakbuddy.edisonandroidexercise.R
import jp.speakbuddy.edisonandroidexercise.model.FactUI

@Composable
fun HistoryFactListSuccessScreen(
    factList: List<FactUI>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.systemBarsPadding()) {
        Text(
            text = stringResource(id = R.string.history_title),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            modifier = modifier.padding(16.dp)
        )

        LazyColumn {
            items(factList) { fact ->
                HistoryFactItem(fact.fact, modifier)
            }
        }
    }
}