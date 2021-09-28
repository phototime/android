package dev.zotov.phototime.feat.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.zotov.phototime.shared.components.Frame
import dev.zotov.phototime.shared.components.Title
import dev.zotov.phototime.shared.theme.PhototimeTheme

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Title(text = "San Fransisco")
    }
}

@Composable
@Preview(device = Devices.PIXEL_3A)
private fun Preview() {
    val navController = rememberNavController()
    PhototimeTheme {
        Frame(navController = navController) {
            HomeScreen(navController = navController)
        }
    }
}



