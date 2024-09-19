package com.aigs.base.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.aigs.base.R

@Composable
fun ComposeBaseTheme(
    enableEdgeToEdge: Boolean = false,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val view = LocalView.current

    val color = Color(0xFFFFFFFF)

    SideEffect {
        val window = (context as? Activity)?.window ?: return@SideEffect
        window.statusBarColor = color.toArgb()

        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.S) {
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                context.theme.obtainStyledAttributes(intArrayOf(R.attr.lightStatusBar))
                    .getBoolean(0, false)
        }

        if (enableEdgeToEdge) {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            window.statusBarColor = android.graphics.Color.TRANSPARENT
            window.navigationBarColor = android.graphics.Color.TRANSPARENT
        } else {
            WindowCompat.setDecorFitsSystemWindows(window, true)
        }
    }

    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            surface = color
        ),
        typography = typography,
        content = content
    )
}