package dev.zotov.phototime.feat.home.components

import androidx.annotation.FloatRange
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.zotov.phototime.shared.components.ActionTime
import dev.zotov.phototime.shared.theme.*
import dev.zotov.phototime.shared.utils.coloredShadow
import dev.zotov.phototime.shared.utils.glassLight
import dev.zotov.phototime.shared.utils.glassShadow

@Composable
fun CurrentPhotoTime(modifier: Modifier = Modifier) {
    Container(modifier = modifier) {
        Header(text = "Golden Hour")
        Spacer(modifier = Modifier.height(10.dp))
        ProgressBar(0.5F)
        Spacer(modifier = Modifier.height(12.dp))
        Footer(timer = "44:55", time = "6:35 AM - 7:48 AM")
    }
}

@Composable
private fun Header(text: String) {
    RowContainer {
        Text(text = text, style = MaterialTheme.typography.tileHeader)
        Text(text = "Now!", style = MaterialTheme.typography.primaryCaption)
    }
}

@Composable
private fun ProgressBar(@FloatRange(from = 0.0, to = 1.0) progress: Float) {
    Box {
        ProgressBarPart(
            fraction = 1F,
            modifier = Modifier.background(ProgressBarColor)
        )
        ProgressBarPart(
            fraction = progress,
            modifier = Modifier
                .background(brush = PrimaryGradient)
                .glassLight()
                .glassShadow()
        )
    }
}

@Composable
private fun ProgressBarPart(
    modifier: Modifier = Modifier,
    @FloatRange(from = 0.0, to = 1.0) fraction: Float
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(fraction = fraction)
            .clip(shape = RoundedCornerShape(10.dp))
            .height(10.dp)
            .composed { modifier }
    )
}


@Composable
private fun Footer(timer: String, time: String) {
    RowContainer {
        Text(text = timer, style = MaterialTheme.typography.timer)
        ActionTime(text = time)
    }
}

@Composable
private fun RowContainer(content: @Composable RowScope.() -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        content()
    }
}

@Composable
private fun Container(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    Box(
        modifier = Modifier
            .composed { modifier }
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .background(TileColor)
            .glassLight()
            .glassShadow()
            .padding(15.dp)
    ) {
        Column {
            content()
        }
    }
}

@Composable
@Preview(backgroundColor = BackgroundPreviewColor, showBackground = true)
private fun Preview() {
    PhototimeTheme {
        Box(
            modifier = Modifier
                .padding(20.dp)
                .width(390.dp)
        ) {
            CurrentPhotoTime()
        }
    }
}