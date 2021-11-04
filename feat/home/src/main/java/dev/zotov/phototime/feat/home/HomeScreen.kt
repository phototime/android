package dev.zotov.phototime.feat.home

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.zotov.phototime.feat.home.components.*
import dev.zotov.phototime.shared.components.*
import dev.zotov.phototime.shared.theme.PhototimeTheme
import dev.zotov.phototime.state.Store
import dev.zotov.phototime.state.actions.ForecastActions
import org.koin.androidx.compose.get

@Composable
fun HomeScreen(navController: NavHostController, scrollState: ScrollState) {
    val store = get<Store>()
    val forecastActions = get<ForecastActions>()
    val forecastState by store.forecastState.collectAsState()

    if (true) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 25.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HomeLoadingScreen()
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Headline(text = "San Fransisco")
        Subtitle(text = "September 26, 2021")
        BigWeatherIcon(icon = WeatherIcons.SunWithCloud)
        Spacer(modifier = Modifier.padding(top = 55.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
        ) {
            WeatherProperty(title = "Temp", value = "32°")
            WeatherProperty(title = "Wind", value = "10km/h")
            WeatherProperty(title = "Humidity", value = "75%")
        }
        CurrentPhotoTime(modifier = Modifier.padding(end = 25.dp, start = 25.dp, top = 50.dp))
        Title(text = "Photo Time")
        PhotoTimeList()
        TitleWithMoreButton()
        WeatherCardCarousel()
    }
}

@Composable
@Preview(device = Devices.PIXEL_3A)
private fun Preview() {
    val navController = rememberNavController()
    val scrollState = rememberScrollState()

    PhototimeTheme {
        Frame(navController = navController) {
            HomeScreen(navController = navController, scrollState = scrollState)
        }
    }
}



