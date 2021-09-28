package dev.zotov.phototime.shared.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp

fun Modifier.glassLight(
) = composed {
    val shadowColor = Color(0xFFFFFFFF).copy(alpha = 0.1f).toArgb()
    val transparent = Color(0xFFFFFFFF).copy(alpha = 0f).toArgb()

    this.drawBehind {

        this.drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            frameworkPaint.color = transparent

            frameworkPaint.setShadowLayer(
                10.dp.toPx(),
                4.dp.toPx(),
                (-1).dp.toPx(),
                shadowColor
            )

            // top
            it.drawRect(
                left = (-4).dp.toPx(),
                top = (-4).dp.toPx(),
                right = this.size.width + 50.dp.toPx(),
                bottom = 1.dp.toPx(),
                paint
            )

            // right
            it.drawRect(
                left = this.size.width + (-1).dp.toPx(),
                top = (-1).dp.toPx(),
                right = this.size.width + 4.dp.toPx(),
                bottom = this.size.height + 25.dp.toPx(),
                paint
            )
        }
    }
}

fun Modifier.glassShadow(
) = composed {
    val shadowColor = Color(0xFF000000).copy(alpha = 0.1f).toArgb()
    val transparent = Color(0xFFFFFFFF).copy(alpha = 0f).toArgb()

    this.drawBehind {

        this.drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            frameworkPaint.color = transparent

            frameworkPaint.setShadowLayer(
                10.dp.toPx(),
                5.dp.toPx(),
                -5.dp.toPx(),
                shadowColor
            )

            // bottom
            it.drawRect(
                left = -(4).dp.toPx(),
                top = this.size.height,
                right = this.size.width,
                bottom = this.size.height + 4.dp.toPx(),
                paint
            )

            // left
            it.drawRect(
                left = (-4).dp.toPx(),
                top = 1.dp.toPx(),
                right = 0f,
                bottom = this.size.height + 25.dp.toPx(),
                paint
            )
        }
    }
}