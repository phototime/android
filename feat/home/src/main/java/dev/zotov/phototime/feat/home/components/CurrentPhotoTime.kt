package dev.zotov.phototime.feat.home.components

import android.text.format.DateUtils
import androidx.annotation.FloatRange
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
import dev.zotov.phototime.shared.logger
import dev.zotov.phototime.shared.theme.*
import dev.zotov.phototime.shared.utils.coloredShadow
import dev.zotov.phototime.shared.utils.glassLight
import dev.zotov.phototime.shared.utils.glassShadow
import dev.zotov.phototime.solarized.SunPhase
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import java.time.Duration
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@OptIn(ObsoleteCoroutinesApi::class)
@Composable
fun CurrentPhotoTime(sunPhase: SunPhase, modifier: Modifier = Modifier) {
    var timer by remember { mutableStateOf(Duration.ZERO) }

    LaunchedEffect(key1 = "Timer") {
        logger.info { "launch timer" }

        timer = Duration.between(LocalDateTime.now(), getTimeEnd(sunPhase))
        logger.info { timer }
        logger.info { getTimeEnd(sunPhase) }
        logger.info { LocalDateTime.now() }

        while (timer.seconds > 0) {
            delay(1000)

            timer = timer.minusSeconds(1)
        }

    }

    val presentation = PhotoTime.fromSunPhase(sunPhase)


    Container(modifier = modifier) {
        Header(text = presentation.title)
        Spacer(modifier = Modifier.height(10.dp))
        ProgressBar(getPhasePercentage(sunPhase, timer))
        Spacer(modifier = Modifier.height(12.dp))
        Footer(timer = formatDuration(timer), time = getSunPhaseTimeRange(sunPhase))
    }

}

private fun formatDuration(duration: Duration): String {
    return DateUtils.formatElapsedTime(duration.seconds)
}

private fun getSunPhaseTimeRange(sunPhase: SunPhase): String {
   val range = getTimeRange(sunPhase)
    return formatTimeRange(range.first, range.second)
}

private fun getTimeEnd(sunPhase: SunPhase): LocalDateTime {
    if (sunPhase is SunPhase.GoldenHour) return sunPhase.end
    if (sunPhase is SunPhase.BlueHour) return sunPhase.end
    if (sunPhase is SunPhase.Day) return sunPhase.end
    throw IllegalArgumentException()
}

private fun getTimeRange(sunPhase: SunPhase): Pair<LocalDateTime, LocalDateTime> {
    if (sunPhase is SunPhase.GoldenHour) return Pair(sunPhase.start, sunPhase.end)
    if (sunPhase is SunPhase.BlueHour) return Pair(sunPhase.start, sunPhase.end)
    if (sunPhase is SunPhase.Day) return Pair(sunPhase.start, sunPhase.end)
    throw IllegalArgumentException()
}

private fun getPhasePercentage(sunPhase: SunPhase, duration: Duration): Float {
    val range = getTimeRange(sunPhase)
    val target = Duration.between(range.first, range.second)
    return 1 - duration.seconds / target.seconds.toFloat()
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
            CurrentPhotoTime(
                SunPhase.GoldenHour(
                    start = LocalDateTime.now().minusMinutes(38),
                    end = LocalDateTime.now().minusMinutes(143),
                )
            )
        }
    }
}