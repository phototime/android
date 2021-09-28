package dev.zotov.phototime.feat.search

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.zotov.phototime.feat.search.components.LoadingWeatherCityCard
import dev.zotov.phototime.feat.search.components.NotFoundMessage
import dev.zotov.phototime.feat.search.components.SearchTextField
import dev.zotov.phototime.feat.search.components.WeatherCityCard
import dev.zotov.phototime.shared.components.Headline
import dev.zotov.phototime.shared.components.Subtitle

@Composable
fun SearchScreen(navController: NavHostController, scrollState: ScrollState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Headline(text = "Pick Location")
        Subtitle(
            text = "Find the area or city that you want to know the perfect photo shoot time and weather",
            modifier = Modifier.width(250.dp)
        )
        Spacer(modifier = Modifier.height(40.dp))
        SearchTextField()

        NotFoundMessage()

//        Row(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(start = 25.dp, end = 25.dp, top = 20.dp)
//        ) {
//            val columnModifier = Modifier.weight(1f)
//            Column(modifier = columnModifier) {
//                LoadingWeatherCityCard()
//                WeatherCityCard(active = false, modifier = Modifier.padding(top = 25.dp))
//                WeatherCityCard(active = false, modifier = Modifier.padding(top = 25.dp))
//            }
//            Spacer(modifier = Modifier.width(30.dp))
//            Column(modifier = columnModifier.padding(top = 30.dp)) {
//                WeatherCityCard(active = false)
//                WeatherCityCard(active = false, modifier = Modifier.padding(top = 25.dp))
//                WeatherCityCard(active = false, modifier = Modifier.padding(top = 25.dp))
//            }
//        }

//        WeatherCityCard(active = true)
    }
}