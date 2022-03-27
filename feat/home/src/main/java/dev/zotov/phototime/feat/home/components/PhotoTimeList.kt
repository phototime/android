package dev.zotov.phototime.feat.home.components

import androidx.compose.runtime.Composable
import dev.zotov.phototime.shared.logger
import dev.zotov.phototime.solarized.SunPhaseList
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Composable
fun PhotoTimeList(sunPhaseList: SunPhaseList) {
    PhotoTimeTile(
        time = PhotoTime.FirstLight,
        actionTime = formatTime(sunPhaseList.firstLight.date),
    )
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
    PhotoTimeTile(
        time = PhotoTime.LastLight,
        actionTime = formatTime(sunPhaseList.lastLight.date),
    )
}

private fun formatTime(dateTime: LocalDateTime): String {
    val date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant())
    return SimpleDateFormat("HH:mm aa", Locale.US).format(date)
}

fun formatTimeRange(start: LocalDateTime, end: LocalDateTime): String {
    return "${formatTime(start)} – ${formatTime(end)}"
}