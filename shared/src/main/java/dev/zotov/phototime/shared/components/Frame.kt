package dev.zotov.phototime.shared.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        Column(Modifier.navigationBarsPadding()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(Color(0xFF090620))
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
            BottomNavigationBar(navController)
        }
}

@Composable
@Preview(device = Devices.PIXEL_3A)
fun FramePreview() {
    val navController = rememberNavController()
    Frame(navController = navController) {

    }
}


