package dev.zotov.phototime

import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.zotov.phototime.feat.home.HomeScreen
import dev.zotov.phototime.shared.theme.PhototimeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        window.statusBarColor = Color.parseColor("#0D0F33")

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        setContent {
            PhototimeTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.Home) {
                    composable(Routes.Home) { HomeScreen() }
                }
            }
        }
    }


}