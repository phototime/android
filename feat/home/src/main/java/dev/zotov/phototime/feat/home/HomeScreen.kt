package dev.zotov.phototime.feat.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.zotov.phototime.feat.home.components.BigWeatherIcon
import dev.zotov.phototime.feat.home.components.WeatherProperty
import dev.zotov.phototime.shared.components.*
import dev.zotov.phototime.shared.theme.PhototimeTheme

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Title(text = "San Fransisco")
        Subtitle(text = "September 26, 2021")
        BigWeatherIcon(icon = WeatherIcons.SunWithCloud)
        Spacer(modifier = Modifier.padding(top = 55.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp)
            ) {
            WeatherProperty(title = "Temp", value = "32°")
            WeatherProperty(title = "Wind", value = "10km/h")
            WeatherProperty(title = "Humidity", value = "75%")
        }
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



