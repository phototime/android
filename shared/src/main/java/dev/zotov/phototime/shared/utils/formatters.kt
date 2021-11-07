package dev.zotov.phototime.shared.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

fun formatDateToUserFriendlyString(date: LocalDateTime): String {
    val month = date.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
    return "$month ${date.dayOfMonth}, ${date.year}";
}

fun formatTimeToUserFriendlyString(time: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    return time.format(formatter)
}