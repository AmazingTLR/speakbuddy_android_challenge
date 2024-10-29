package jp.speakbuddy.edisonandroidexercise

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import jp.speakbuddy.edisonandroidexercise.ui.fact.FactListScreen
import jp.speakbuddy.edisonandroidexercise.ui.history.HistoryFactListScreen

@Composable
fun FactNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.factListScreen, builder = {
        composable(Routes.factListScreen) {
            FactListScreen(navController = navController)
        }

        composable(route = Routes.factHistoryListScreen) {
            HistoryFactListScreen()
        }
    })
}

object Routes {
    var factListScreen = "factListScreen"
    var factHistoryListScreen = "factHistoryListScreen"
}
