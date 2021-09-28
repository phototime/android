package dev.zotov.phototime.shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
fun Title(text: String, companion: @Composable RowScope.() -> Unit = {}) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 30.dp, start = 25.dp, end = 25.dp),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.title,
        )
        Spacer(modifier = Modifier.weight(1f))
        companion()
    }

}

@Preview(showBackground = true, backgroundColor = BackgroundPreviewColor)
@Composable
private fun Preview() {
    PhototimeTheme {
        Title(text = "Photo Time")
    }
}