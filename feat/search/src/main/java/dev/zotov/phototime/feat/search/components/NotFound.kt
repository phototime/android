package dev.zotov.phototime.feat.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.zotov.phototime.feat.search.R
import dev.zotov.phototime.shared.theme.BackgroundPreviewColor
import dev.zotov.phototime.shared.theme.PhototimeTheme
import dev.zotov.phototime.shared.theme.subtitle

@Composable
fun ColumnScope.NotFoundMessage() {
    Spacer(modifier = Modifier.weight(2f))
    SadIcon()
    Spacer(modifier = Modifier.height(25.dp))
    Message()
    Spacer(modifier = Modifier.weight(5f))
}

@Composable
private fun SadIcon() {
    val painter = painterResource(id = R.drawable.sad)
    Image(
        painter = painter,
        contentDescription = "sad icon",
        modifier = Modifier.width(56.dp),
        contentScale = ContentScale.Fit,
    )
}

@Composable
private fun Message() {
    Text(
        text = "We searched everywhere but couldn't find this location",
        modifier = Modifier.width(168.dp),
        style = MaterialTheme.typography.subtitle,
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = BackgroundPreviewColor)
private fun Preview() {
    PhototimeTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            NotFoundMessage()
        }
    }
}