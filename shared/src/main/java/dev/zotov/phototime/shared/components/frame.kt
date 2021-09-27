package dev.zotov.phototime.shared.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import dev.zotov.phototime.shared.R
import dev.zotov.phototime.shared.theme.backgroundGradient

@Composable
fun Frame(content: @Composable BoxScope.() -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = backgroundGradient)
    ) {
        Image(
            painter = painterResource(id = R.drawable.blur),
            contentDescription = "blur",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.TopEnd,
        )
        content()
    }
}