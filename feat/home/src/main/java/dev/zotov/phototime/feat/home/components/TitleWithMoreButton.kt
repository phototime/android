package dev.zotov.phototime.feat.home.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.zotov.phototime.shared.theme.BackgroundPreviewColor
import dev.zotov.phototime.shared.theme.PhototimeTheme
import dev.zotov.phototime.shared.components.Title
import dev.zotov.phototime.shared.theme.titleCompanion

@Composable
fun TitleWithMoreButton() {
    Title(text = "Today") {
        Text(
            text = "More",
            style = MaterialTheme.typography.titleCompanion,
        )
    }
}


@Composable
@Preview(showBackground = true, backgroundColor = BackgroundPreviewColor)
private fun Preview() {
    PhototimeTheme {
        TitleWithMoreButton()
    }
}