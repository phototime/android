package dev.zotov.phototime.feat.home.components

import android.annotation.SuppressLint
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
import androidx.compose.ui.unit.dp
import dev.zotov.phototime.shared.components.ActionTime
import dev.zotov.phototime.shared.theme.*
import dev.zotov.phototime.shared.utils.glassLight
import dev.zotov.phototime.shared.utils.glassShadow
import dev.zotov.phototime.solarized.SunPhase
import dev.zotov.phototime.state.blocs.CurrentSunPhaseBloc
import dev.zotov.phototime.state.state.CurrentSunPhaseState
import kotlinx.coroutines.ObsoleteCoroutinesApi
import org.koin.androidx.compose.get
import java.time.Duration
import java.time.LocalDateTime

@OptIn(ObsoleteCoroutinesApi::class)
@Composable
fun CurrentPhotoTime(modifier: Modifier = Modifier) {
    when (val state = get<CurrentSunPhaseBloc>().state.collectAsState().value) {
        is CurrentSunPhaseState.Idle -> {
            val (sunPhase, duration) = state
            val presentation = PhotoTime.fromSunPhase(sunPhase)

            Container(modifier = modifier) {
                Header(text = presentation.title)
                Spacer(modifier = Modifier.height(10.dp))
                ProgressBar(getPhasePercentage(sunPhase, duration))
                Spacer(modifier = Modifier.height(12.dp))
                Footer(timer = formatDuration(duration), time = getSunPhaseTimeRange(sunPhase))
            }
        }
        else -> Unit
    }
}

private fun formatDuration(duration: Duration): String {
    return DateUtils.formatElapsedTime(duration.seconds)
}

private fun getSunPhaseTimeRange(sunPhase: SunPhase): String {
    val range = getTimeRange(sunPhase)
    return formatTimeRange(range.first, range.second)
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

@SuppressLint("UnnecessaryComposedModifier")
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
        modifier = modifier
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
