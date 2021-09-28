package dev.zotov.phototime

import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.zotov.phototime.feat.home.HomeScreen
import dev.zotov.phototime.feat.search.SearchScreen
import dev.zotov.phototime.shared.Routes
import dev.zotov.phototime.shared.components.Frame

@Composable
fun MainFeature(mainNavController: NavHostController) {
    val nestedNavController = rememberNavController()
    Frame(navController = nestedNavController) {
        val homeScreenScrollState = rememberScrollState()
        val searchScreenScrollState = rememberScrollState()

        NavHost(navController = nestedNavController, startDestination = Routes.Main.Search) {
            composable(Routes.Main.Home) {
                HomeScreen(
                    navController = nestedNavController,
                    scrollState = homeScreenScrollState
                )
            }
            composable(Routes.Main.Search) {
                SearchScreen(
                    navController = nestedNavController,
                    scrollState = searchScreenScrollState
                )
            }
            composable(Routes.Main.Settings) {
                HomeScreen(
                    navController = nestedNavController,
                    scrollState = rememberScrollState()
                )
            }
        }
    }
}