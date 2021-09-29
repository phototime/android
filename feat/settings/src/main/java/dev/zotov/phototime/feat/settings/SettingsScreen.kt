package dev.zotov.phototime.feat.settings

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import dev.zotov.phototime.shared.components.Headline

@Composable
fun SettingsScreen(navController: NavHostController, scrollState: ScrollState) {
    Container(scrollState = scrollState) {
        Headline(text = "Settings")
    }
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