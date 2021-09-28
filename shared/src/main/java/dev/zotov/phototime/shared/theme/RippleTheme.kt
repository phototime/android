package dev.zotov.phototime.shared.theme

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object RippleCustomTheme: RippleTheme {
    @Composable
    override fun defaultColor(): Color = Color(0xFFFFFFFF).copy(alpha = 0.1f)

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(
        pressedAlpha = 0.10f,
        focusedAlpha = 0.12f,
        draggedAlpha = 0.08f,
        hoveredAlpha = 0.04f
    )
}