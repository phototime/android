package dev.zotov.phototime.feat.home

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import dev.zotov.phototime.state.state.ForecastState
import dev.zotov.phototime.state.state.SunPhaseState
import org.koin.androidx.compose.get
import java.time.LocalTime

@Composable
fun HomeScreen(navController: NavHostController, scrollState: ScrollState) {
    val store = get<Store>()

    // state
    val forecastState = store.forecastState.collectAsState().value
    val sunPhaseState = store.sunPhaseState.collectAsState().value

    val loading = forecastState is ForecastState.Loading || sunPhaseState is SunPhaseState.Loading

    if (loading) {
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
        if (forecastState is ForecastState.Idle) {
            Headline(text = forecastState.location.name)
            Subtitle(text = forecastState.date)
            BigWeatherIcon(type = forecastState.type)
        }

        Spacer(modifier = Modifier.padding(top = 55.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
        ) {
            if (forecastState is ForecastState.Idle) {
                WeatherProperty(title = "Temp", value = "${forecastState.temp}°")
                WeatherProperty(title = "Wind", value = "${forecastState.wind}km/h")
                WeatherProperty(title = "Humidity", value = "${forecastState.humidity}%")
            }
        }
        if (sunPhaseState is SunPhaseState.Idle) {
            val current = sunPhaseState.get(LocalTime.now())

            if (current != null) {
                CurrentPhotoTime(
                    sunPhase = current,
                    modifier = Modifier.padding(end = 25.dp, start = 25.dp, top = 50.dp),
                )
            }

            Title(text = "Photo Time")
            PhotoTimeList(sunPhaseList = sunPhaseState.list)
        }

        // Hourly forecast
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



