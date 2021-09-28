package dev.zotov.phototime.feat.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.zotov.phototime.shared.theme.BackgroundPreviewColor
import dev.zotov.phototime.shared.theme.propertyName
import dev.zotov.phototime.shared.theme.propertyValue

@Composable
fun RowScope.WeatherProperty(title: String, value: String) {
    Container {
        Title(text = title)
        Spacer(modifier = Modifier.height(5.dp))
        Value(text = value)
    }
}

@Composable
private fun RowScope.Container(content: @Composable ColumnScope.() -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .weight(0.333F)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}

@Composable
private fun Title(text: String) {
    Text(text = text, style = MaterialTheme.typography.propertyName)
}

@Composable
private fun Value(text: String) {
    Text(text = text, style = MaterialTheme.typography.propertyValue)
}

@Composable
@Preview(showBackground = true, backgroundColor = BackgroundPreviewColor)
private fun Preview() {
    Row(
        modifier = Modifier
            .padding(20.dp)
            .height(75.dp)
            .width(300.dp)
    ) {
        WeatherProperty(
            title = "Temp",
            value = "32°"
        )
    }
}