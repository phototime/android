package dev.zotov.phototime.shared.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.zotov.phototime.shared.theme.BackgroundPreviewColor
import dev.zotov.phototime.shared.theme.PhototimeTheme
import dev.zotov.phototime.shared.theme.subtitle

@Composable
fun Subtitle(modifier: Modifier = Modifier, text: String) {
    Text(
        text = text,
        modifier = Modifier
            .padding(top = 10.dp)
            .composed { modifier },
        style = MaterialTheme.typography.subtitle
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF090620)
private fun Preview() {
    PhototimeTheme {
        Box(modifier = Modifier.width(390.dp)) {
            Headline(
                text = "September 26, 2021",
            )
        }
    }
}

@Composable
fun SubtitleLoading() {
    LoadingShape(width = 150.dp, height = 20.dp)
}

@Composable
@Preview(showBackground = true, backgroundColor = BackgroundPreviewColor)
private fun PreviewLoading() {
    PhototimeTheme {
        SubtitleLoading()
    }
}