package dev.zotov.phototime.shared.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.zotov.phototime.shared.R
import dev.zotov.phototime.shared.theme.backgroundGradient

@Composable
fun Frame(navController: NavHostController, content: @Composable BoxScope.() -> Unit) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController
            )
        }
    ) {
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
            Box(modifier = Modifier.padding(bottom = 79.dp)) {
                content()
            }
        }
    }
}

@Composable
@Preview(device = Devices.PIXEL_3A)
fun FramePreview() {
    val navController = rememberNavController()
    Frame(navController = navController) {

    }
}


