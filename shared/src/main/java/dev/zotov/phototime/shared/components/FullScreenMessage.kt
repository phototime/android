package dev.zotov.phototime.shared.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.zotov.phototime.shared.theme.BackgroundPreviewColor
import dev.zotov.phototime.shared.theme.PhototimeTheme
import dev.zotov.phototime.shared.theme.subtitle
import dev.zotov.phototime.shared.R

@Composable
fun ColumnScope.FullScreenMessage(
    @DrawableRes iconId: Int,
    text: String,
    textSize: Dp = 200.dp,
    contentDescription: String? = "message icon",
    startWeight: Float = 2f,
    endWeight: Float = 5f,
) {
    Spacer(modifier = Modifier.weight(startWeight))
    Icon(iconId = iconId, contentDescription = contentDescription)
    Spacer(modifier = Modifier.height(15.dp))
    Message(text = text, textSize = textSize)
    Spacer(modifier = Modifier.weight(endWeight))
}

@Composable
private fun Icon(@DrawableRes iconId: Int, contentDescription: String?) {
    val painter = painterResource(id = iconId)
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = Modifier.width(64.dp),
        contentScale = ContentScale.Fit,
    )
}

@Composable
private fun Message(text: String, textSize: Dp) {
    Text(
        text = text,
        modifier = Modifier.width(textSize),
        style = MaterialTheme.typography.subtitle,
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = BackgroundPreviewColor)
private fun Preview() {
    PhototimeTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FullScreenMessage(
                startWeight = 1f,
                endWeight = 1f,
                textSize = 168.dp,
                iconId = R.drawable.search,
                contentDescription = "sad icon",
                text = "We searched everywhere but couldn't find this location",
            )
        }
    }
}

