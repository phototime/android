package dev.zotov.phototime.feat.settings

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import dev.zotov.phototime.shared.components.FullScreenMessage
import dev.zotov.phototime.shared.components.Headline

@Composable
fun SettingsScreen(navController: NavHostController, scrollState: ScrollState) {
    Container(scrollState = scrollState) {
        Headline(text = "Settings")
        Message()
    }
}

@Composable
private fun ColumnScope.Message() {
    FullScreenMessage(
        iconId = R.drawable.cool,
        text = "The developers are very lazy and have not yet made this screen\nA lot of cool stuff coming soon!",
        startWeight = 4f,
        endWeight = 5f,
        contentDescription = "cool icon",
    )

}

@Composable
private fun Container(scrollState: ScrollState, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        content()
    }
}