package dev.zotov.phototime.feat.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.zotov.phototime.shared.logger
import dev.zotov.phototime.shared.theme.BackgroundPreviewColor
import dev.zotov.phototime.shared.theme.PhototimeTheme
import dev.zotov.phototime.solarized.Solarized
import dev.zotov.phototime.solarized.SunPhaseList
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.*

@Composable
fun PhotoTimeList(sunPhaseList: SunPhaseList) {
    PhotoTimeTile(
        time = PhotoTime.BlueHour,
        actionTime = formatTimeRange(
            sunPhaseList.morningBlueHour.start,
            sunPhaseList.morningBlueHour.end
        ),
    )
    PhotoTimeTile(
        time = PhotoTime.GoldenHour,
        actionTime = formatTimeRange(
            sunPhaseList.morningGoldenHour.start,
            sunPhaseList.morningGoldenHour.end
        ),
    )
    PhotoTimeTile(
        time = PhotoTime.Sunrise,
        actionTime = formatTime(sunPhaseList.sunrise.date),
    )
    PhotoTimeTile(
        time = PhotoTime.Day,
        actionTime = formatTimeRange(
            sunPhaseList.day.start,
            sunPhaseList.day.end
        ),
    )
    PhotoTimeTile(
        time = PhotoTime.GoldenHour,
        actionTime = formatTimeRange(
            sunPhaseList.eveningGoldenHour.start,
            sunPhaseList.eveningBlueHour.end
        ),
    )
    PhotoTimeTile(
        time = PhotoTime.Sunset,
        actionTime = formatTime(sunPhaseList.sunset.date),
    )
    PhotoTimeTile(
        time = PhotoTime.BlueHour,
        actionTime = formatTimeRange(
            sunPhaseList.eveningBlueHour.start,
            sunPhaseList.eveningBlueHour.end
        ),
    )
}

private fun formatTime(dateTime: LocalDateTime): String {
    val date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant())
    return SimpleDateFormat("hh:mm aa", Locale.US).format(date)
}

fun formatTimeRange(start: LocalDateTime, end: LocalDateTime): String {
    return "${formatTime(start)} – ${formatTime(end)}"
}

@Composable
@Preview(showBackground = true, backgroundColor = BackgroundPreviewColor)
private fun Preview() {
    val date = Instant.ofEpochSecond(1651680967).atZone(ZoneOffset.UTC).toLocalDateTime()
    val latitude = 48.85881
    val longitude = 2.32003
    val solarized = Solarized(latitude, longitude, date)

    PhototimeTheme {
        Column(modifier = Modifier.width(365.dp).height(1000.dp)) {
            PhotoTimeList(sunPhaseList = solarized.list!!)
        }
    }
}