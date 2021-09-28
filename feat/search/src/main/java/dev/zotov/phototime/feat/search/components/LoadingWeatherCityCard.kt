package dev.zotov.phototime.feat.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.zotov.phototime.shared.theme.BackgroundPreviewColor
import dev.zotov.phototime.shared.theme.LoadingElementColor
import dev.zotov.phototime.shared.theme.PhototimeTheme

@Composable
fun LoadingWeatherCityCard() {
    WeatherCityCardContainer(active = false) {
        BaseLoadingRect(height = 30.dp, width = 48.dp)
        Spacer(modifier = Modifier.height(10.dp))
        BaseLoadingRect(height = 10.dp, width = 67.dp)
        Spacer(modifier = Modifier.height(5.dp))
        BaseLoadingRect(height = 10.dp, width = 115.dp)
        Spacer(modifier = Modifier.height(5.dp))
        BaseLoadingRect(height = 14.dp, width = 85.dp)
    }
}

@Composable
private fun BaseLoadingRect(height: Dp, width: Dp) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(LoadingElementColor)
            .height(height)
            .width(width)
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = BackgroundPreviewColor)
private fun Preview() {
    PhototimeTheme {
        LoadingWeatherCityCard()
    }
}