package dev.zotov.phototime.feat.home.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable

@Composable
fun ColumnScope.PhotoTimeList() {
    PhotoTimeTile(
        time = PhotoTime.FirstLight,
        actionTime = "6:27 AM",
    )
    PhotoTimeTile(
        time = PhotoTime.BlueHour,
        actionTime = "6:29 AM – 6:39 AM",
    )
    PhotoTimeTile(
        time = PhotoTime.GoldenHour,
        actionTime = "6:39 AM – 7:30 AM",
    )
    PhotoTimeTile(
        time = PhotoTime.Sunrise,
        actionTime = "6:55 AM",
    )
    PhotoTimeTile(
        time = PhotoTime.Day,
        actionTime = "7:30 AM – 6:39 PM",
    )
    PhotoTimeTile(
        time = PhotoTime.GoldenHour,
        actionTime = "6:39 PM – 7:30 PM",
    )
    PhotoTimeTile(
        time = PhotoTime.Sunset,
        actionTime = "7:14 PM",
    )
    PhotoTimeTile(
        time = PhotoTime.BlueHour,
        actionTime = "7:30 PM – 7:40 PM",
    )
    PhotoTimeTile(
        time = PhotoTime.LastLight,
        actionTime = "6:27 AM",
    )
}