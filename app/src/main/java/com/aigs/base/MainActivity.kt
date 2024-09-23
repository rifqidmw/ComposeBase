package com.aigs.base

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.aigs.base.data.local.LanguagePreferences
import com.aigs.base.data.repository.SettingsRepositoryImpl
import com.aigs.base.ui.navigations.AppNavigation
import com.aigs.base.ui.theme.ComposeBaseTheme
import com.aigs.base.utils.Utils.setLocale
import com.aigs.base.utils.Utils.updateLanguage
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val languagePreferences: LanguagePreferences by inject()

    override fun attachBaseContext(newBase: Context) {
        val language = runBlocking { languagePreferences.languageFlow.first() }
        super.attachBaseContext(setLocale(newBase, language))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            ComposeBaseTheme {
                val code by languagePreferences.languageFlow.collectAsState(initial = "en")

                LaunchedEffect(code) {
                    updateLanguage(this@MainActivity, code)
                }

                AppNavigation()
            }
        }
    }
}