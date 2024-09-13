package com.aigs.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.aigs.base.presentation.navigation.AppNavigation
import com.aigs.base.ui.theme.ComposeBaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            ComposeBaseTheme {
                AppNavigation()
            }
        }
    }
}