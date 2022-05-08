package dev.zotov.phototime.shared.components

import android.util.TypedValue
import android.util.TypedValue.complexToDimensionPixelSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.zotov.phototime.shared.R
import dev.zotov.phototime.shared.Routes

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val backStateEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStateEntry?.destination?.route

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF090620))
            .height(56.dp)
            .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        HomeIcon(active = currentRoute == Routes.Main.Home, navController = navController)
        SearchIcon(active = currentRoute == Routes.Main.Search, navController = navController)
//            SettingsIcon(
//                active = currentRoute == Routes.Main.Settings,
//                navController = navController
//            )
    }
}

@Composable
@Preview()
fun BottomNavigationBarPreview() {
    val navController = rememberNavController()
    Box(modifier = Modifier.width(390.dp)) {
        BottomNavigationBar(navController = navController)
    }
}

@Composable
private fun RowScope.HomeIcon(active: Boolean, navController: NavHostController) {
    val iconId = if (active) R.drawable.home_active else R.drawable.home
    Icon(iconId) { navController.navigate(Routes.Main.Home) { popUpTo(0) } }
}

@Composable
private fun RowScope.SearchIcon(active: Boolean, navController: NavHostController) {
    val iconId = if (active) R.drawable.search_active else R.drawable.search
    Icon(iconId) { navController.navigate(Routes.Main.Search) { popUpTo(0) } }
}

@Composable
private fun RowScope.SettingsIcon(active: Boolean, navController: NavHostController) {
    val iconId = if (active) R.drawable.settings_active else R.drawable.settings
    Icon(iconId) { navController.navigate(Routes.Main.Settings) { popUpTo(0) } }
}

@Composable
private fun RowScope.Icon(iconId: Int, onTap: () -> Unit) {
    Box(
        modifier = Modifier
            .weight(1f)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onTap() }
    ) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
                .align(Alignment.Center)
        )
    }
}

