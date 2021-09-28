package dev.zotov.phototime.feat.home.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun WeatherCardCarousel() {
    val amountOfCards = 10
    val selected = 5
    val offset = with(LocalDensity.current) { (selected * 162).dp.toPx() }.toInt()
    val scrollState = rememberScrollState(initial = offset)

    Row(modifier = Modifier.horizontalScroll(scrollState)) {
        for (page in 0 until amountOfCards) {
            val endPadding = when (page) {
                amountOfCards - 1 -> 25.dp
                else -> 0.dp
            }

            WeatherCard(
                selected = page == selected,
                modifier = Modifier.padding(start = 25.dp, end = endPadding, top = 10.dp)
            )
        }
    }
}