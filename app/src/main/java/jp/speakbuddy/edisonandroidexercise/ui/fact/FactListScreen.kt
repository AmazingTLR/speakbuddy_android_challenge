package jp.speakbuddy.edisonandroidexercise.ui.fact

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import jp.speakbuddy.edisonandroidexercise.ui.common.screen.ErrorScreen
import jp.speakbuddy.edisonandroidexercise.ui.common.screen.LoadingScreen
import jp.speakbuddy.edisonandroidexercise.R
import jp.speakbuddy.edisonandroidexercise.Routes
import jp.speakbuddy.edisonandroidexercise.states.FactListState
import jp.speakbuddy.edisonandroidexercise.ui.theme.EdisonAndroidExerciseTheme

@Composable
fun FactListScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: FactViewModel = hiltViewModel()
) {
    val factListState by viewModel.factListStateFlow.collectAsStateWithLifecycle()

    factListState.let { factList ->
        when (factList) {
            FactListState.Error -> ErrorScreen(
                modifier = modifier,
                message = stringResource(R.string.no_fact_found_error)
            )

            FactListState.Loading -> LoadingScreen(modifier = modifier)
            is FactListState.Success -> {
                FactListSuccessScreen(
                    factList = factList.factList,
                    onRequestForNextPage = viewModel::loadMoreFacts,
                    hasMore = factList.hasMoreContent,
                    onFactSeen = viewModel::markFactAsSeen,
                    onNavigateToFactHistoryList = { navigateToFactHistoryList(navController) },
                    modifier = modifier
                )
            }
        }

    }
}

private fun navigateToFactHistoryList(navController: NavController) {
    navController.navigate(Routes.factHistoryListScreen) {
        popUpTo(navController.graph.startDestinationId) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Preview
@Composable
private fun FactScreenPreview() {
    EdisonAndroidExerciseTheme {
        val navController = rememberNavController()
        FactListScreen(navController)
    }
}
