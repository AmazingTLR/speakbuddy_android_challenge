package jp.speakbuddy.edisonandroidexercise.ui.history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import jp.speakbuddy.edisonandroidexercise.R
import jp.speakbuddy.edisonandroidexercise.states.HistoryFactListState
import jp.speakbuddy.edisonandroidexercise.ui.common.screen.ErrorScreen
import jp.speakbuddy.edisonandroidexercise.ui.common.screen.LoadingScreen
import jp.speakbuddy.edisonandroidexercise.ui.theme.EdisonAndroidExerciseTheme

@Composable
fun HistoryFactListScreen(
    modifier: Modifier = Modifier,
    viewModel: HistoryFactViewModel = hiltViewModel()
) {
    val historyFactListState by viewModel.historyFactListStateFlow.collectAsStateWithLifecycle()

    historyFactListState.let { historyFactList ->
        when (historyFactList) {
            HistoryFactListState.Error -> ErrorScreen(modifier = modifier, message = stringResource(R.string.no_fact_found_error))
            HistoryFactListState.Loading -> LoadingScreen(modifier = modifier)
            is HistoryFactListState.Success -> {
                HistoryFactListSuccessScreen(
                    factList = historyFactList.factList,
                    modifier = modifier
                )
            }
        }

    }
}

@Preview
@Composable
private fun FactScreenPreview() {
    EdisonAndroidExerciseTheme {
        HistoryFactListScreen()
    }
}