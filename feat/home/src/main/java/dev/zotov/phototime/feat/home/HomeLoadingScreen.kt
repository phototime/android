package dev.zotov.phototime.feat.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.zotov.phototime.shared.components.HeadlineLoading
import dev.zotov.phototime.shared.components.LoadingShape
import dev.zotov.phototime.shared.components.SubtitleLoading
import dev.zotov.phototime.shared.components.TitleLoading

@Composable
fun HomeLoadingScreen() {
    Spacer(modifier = Modifier.height(55.dp))
    HeadlineLoading()
    Spacer(modifier = Modifier.height(10.dp))
    SubtitleLoading()
    Spacer(modifier = Modifier.height(55.dp))
    Icon()
    Spacer(modifier = Modifier.height(60.dp))
    WeatherCard()
    Spacer(modifier = Modifier.height(50.dp))
    PhotoTimeCard()
    Spacer(modifier = Modifier.height(30.dp))
    TitleLoading()
    Spacer(modifier = Modifier.height(2.dp))
    for (i in 0 until 9) {
        PhotoTimeTile()
    }
    Spacer(modifier = Modifier.height(30.dp))
    TitleLoading()
    Spacer(modifier = Modifier.height(10.dp))
    WeatherCardList()
}

@Composable
private fun Icon() {
    LoadingShape(width = 200.dp, height = 200.dp, borderRadius = 25.dp)
}

@Composable
private fun WeatherCard() {
    Row(
        modifier = Modifier.padding(horizontal = 27.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        LoadingShape(width = 64.dp, height = 48.dp, borderRadius = 10.dp)
        LoadingShape(width = 64.dp, height = 48.dp, borderRadius = 10.dp)
        LoadingShape(width = 64.dp, height = 48.dp, borderRadius = 10.dp)
    }
}

@Composable
private fun PhotoTimeCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFFFFFFFF).copy(alpha = 0.07f))
            .padding(15.dp)
    ) {
        LoadingShape(width = 122.dp, height = 18.dp, opacity = 0.05f)
        Spacer(modifier = Modifier.height(10.dp))
        LoadingShape(height = 10.dp, opacity = 0.05f, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(15.dp))
        LoadingShape(width = 211.dp, height = 18.dp, opacity = 0.05f)
    }
}

@Composable
private fun PhotoTimeTile() {
    val width = (75..150).random().dp

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(Color(0xFFFFFFFF).copy(alpha = 0.07f))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        LoadingShape(width = 20.dp, height = 20.dp, borderRadius = 34.dp, opacity = 0.05f)
        Spacer(modifier = Modifier.width(10.dp))
        LoadingShape(width = width, height = 16.dp, opacity = 0.05f)
    }
}

@Composable
private fun WeatherCardList() {
    Row(modifier = Modifier.fillMaxWidth()) {
        for (i in 0 until 3) {
            Row(
                modifier = Modifier
                    .width(137.dp)
                    .height(72.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFFFFFFFF).copy(alpha = 0.07f))
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                LoadingShape(width = 50.dp, height = 45.dp, borderRadius = 15.dp, opacity = 0.05f)
                Spacer(modifier = Modifier.width(10.dp))
                Column(verticalArrangement = Arrangement.Center) {
                    LoadingShape(width = 47.dp, height = 10.dp, opacity = 0.05f)
                    Spacer(modifier = Modifier.height(8.dp))
                    LoadingShape(width = 35.dp, height = 10.dp, opacity = 0.05f)
                }
            }
            Spacer(modifier = Modifier.width(25.dp))
        }
    }
}