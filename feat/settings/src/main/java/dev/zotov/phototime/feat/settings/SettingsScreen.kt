package dev.zotov.phototime.feat.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.zotov.phototime.shared.components.Headline
import dev.zotov.phototime.shared.theme.subtitle

@Composable
fun SettingsScreen(navController: NavHostController, scrollState: ScrollState) {
    Container(scrollState = scrollState) {
        Headline(text = "Settings")
        Message()
    }
}

@Composable
private fun ColumnScope.Message() {
    Spacer(modifier = Modifier.weight(4f))
    CoolIcon()
    Spacer(modifier = Modifier.height(25.dp))
    MessageText()
    Spacer(modifier = Modifier.weight(5f))
}

@Composable
private fun CoolIcon() {
    val painter = painterResource(id = R.drawable.cool)
    Image(
        painter = painter,
        contentDescription = "cool icon",
        modifier = Modifier.size(56.dp)
    )
}

@Composable
private fun MessageText() {
    Text(
        text = "The developers are very lazy and have not yet made this screen\nA lot of cool stuff coming soon!",
        modifier = Modifier.width(202.dp),
        style = MaterialTheme.typography.subtitle,
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