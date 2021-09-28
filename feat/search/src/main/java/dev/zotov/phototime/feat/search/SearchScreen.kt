package dev.zotov.phototime.feat.search

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.zotov.phototime.shared.components.Headline
import dev.zotov.phototime.shared.components.Subtitle

@Composable
fun SearchScreen(navController: NavHostController, scrollState: ScrollState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Headline(text = "Pick Location")
        Subtitle(
            text = "Find the area or city that you want to know the perfect photo shoot time and weather",
            modifier = Modifier.width(250.dp)
        )
    }
}