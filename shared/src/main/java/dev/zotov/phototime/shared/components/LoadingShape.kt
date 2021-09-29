package dev.zotov.phototime.shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LoadingShape(
    modifier: Modifier = Modifier,
    width: Dp = 0.dp,
    height: Dp = 0.dp,
    borderRadius: Dp = 4.dp,
    opacity: Float = 0.07f,
    content: @Composable () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(borderRadius))
            .background(
                Color(0xFFFFFFFF).copy(alpha = opacity)
            )
            .composed { modifier }
    ) {
        content()
    }
}