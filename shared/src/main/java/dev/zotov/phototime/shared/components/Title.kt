package dev.zotov.phototime.shared.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.zotov.phototime.shared.theme.BackgroundPreviewColor
import dev.zotov.phototime.shared.theme.PhototimeTheme
import dev.zotov.phototime.shared.theme.title

@Composable
fun Title(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.title,
        modifier = Modifier
            .padding(top = 30.dp, start = 25.dp)
            .fillMaxWidth()
    )
}

@Preview(showBackground = true, backgroundColor = BackgroundPreviewColor)
@Composable
private fun Preview() {
    PhototimeTheme {
        Title(text = "Photo Time")
    }
}