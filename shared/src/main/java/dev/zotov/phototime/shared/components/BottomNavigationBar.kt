package dev.zotov.phototime.shared.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.zotov.phototime.shared.R
import dev.zotov.phototime.shared.Routes

@Composable
fun BottomNavigationBar(modifier: Modifier = Modifier, navController: NavHostController) {
    val backStateEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStateEntry?.destination?.route

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp)
            .height(64.dp)
            .composed { modifier }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 48.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            HomeIcon(active = currentRoute == Routes.Main.Home, navController = navController)
            SearchIcon(active = currentRoute == Routes.Main.Search, navController = navController)
            SettingsIcon(active = currentRoute == Routes.Main.Settings, navController = navController)
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF090620)
fun BottomNavigationBarPreview() {
    val navController = rememberNavController()
    Box(modifier = Modifier.width(390.dp)) {
        BottomNavigationBar(navController = navController)
    }
}

@Composable
private fun HomeIcon(active: Boolean, navController: NavHostController) {
    val iconId = if (active) R.drawable.home_active else R.drawable.home
    Icon(iconId) { navController.navigate(Routes.Main.Home) }
}

@Composable
private fun SearchIcon(active: Boolean, navController: NavHostController) {
    val iconId = if (active) R.drawable.search_active else R.drawable.search
    Icon(iconId) { navController.navigate(Routes.Main.Search) }
}

@Composable
private fun SettingsIcon(active: Boolean, navController: NavHostController) {
    val iconId = if (active) R.drawable.settings_active else R.drawable.settings
    Icon(iconId) { navController.navigate(Routes.Main.Settings) }
}

@Composable
private fun Icon(iconId: Int, onTap: () -> Unit) {
    Image(
        painter = painterResource(id = iconId),
        contentDescription = null,
        modifier = Modifier
            .size(32.dp)
            .clickable { onTap() }
    )
}

