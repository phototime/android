package dev.zotov.phototime

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.zotov.phototime.feat.home.HomeScreen
import dev.zotov.phototime.shared.Routes
import dev.zotov.phototime.shared.components.Frame

@Composable
fun MainFeature(mainNavController: NavHostController) {
    val nestedNavController = rememberNavController()
    Frame(navController = nestedNavController) {
        NavHost(navController = nestedNavController, startDestination = Routes.Main.Home) {
            composable(Routes.Main.Home) { HomeScreen(navController = nestedNavController) }
            composable(Routes.Main.Search) { HomeScreen(navController = nestedNavController) }
            composable(Routes.Main.Settings) { HomeScreen(navController = nestedNavController) }
        }
    }
}