package dev.zotov.phototime

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.zotov.phototime.feat.home.HomeScreen
import dev.zotov.phototime.feat.search.SearchScreen
import dev.zotov.phototime.feat.settings.SettingsScreen
import dev.zotov.phototime.shared.Routes
import dev.zotov.phototime.shared.components.Frame

@Composable
fun MainFeature(mainNavController: NavHostController) {
    val nestedNavController = rememberNavController()
    Frame(navController = nestedNavController) {
        val homeScreenScrollState = rememberScrollState()
        val searchScreenScrollState = rememberScrollState()
        val settingsScrollState = rememberScrollState()

        NavHost(navController = nestedNavController, startDestination = Routes.Main.Home) {
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
                SettingsScreen(
                    navController = nestedNavController,
                    scrollState = settingsScrollState,
                )
            }
        }
    }
}

@Preview
@Composable
fun Test() {
    Text(text = "123")
}