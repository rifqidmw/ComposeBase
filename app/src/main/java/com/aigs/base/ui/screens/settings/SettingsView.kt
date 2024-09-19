package com.aigs.base.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aigs.base.R
import com.aigs.base.common.AppConstants.Route
import com.aigs.base.ui.components.BaseButton
import com.aigs.base.ui.components.ButtonStyle
import com.aigs.base.ui.components.LanguageSelectionItem
import com.aigs.base.ui.theme.dangerRed
import com.aigs.base.ui.theme.nunitoSans
import com.aigs.base.ui.theme.primaryBlack
import com.aigs.base.ui.theme.raleway

@Composable
fun SettingsView(navController: NavController, viewModel: SettingsViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val navigationEvent by viewModel.navigationEvent.collectAsState()

    LaunchedEffect(navigationEvent) {
        when (navigationEvent) {
            is SettingsNavigationEvent.Logout -> {
                navController.navigate(Route.ONBOARDING)
                viewModel.onNavigationEventHandled()
            }

            else -> {}
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = stringResource(R.string.label_title_settings),
            fontFamily = raleway,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            lineHeight = 36.sp,
            color = primaryBlack,
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.label_sub_language),
            fontFamily = raleway,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = primaryBlack,
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(uiState.listLanguages) { language ->
                LanguageSelectionItem(
                    language = language,
                    isSelected = language.code == uiState.selectedLanguage,
                    onSelect = {
                        viewModel.setLanguage(language.code)
                        navController.popBackStack()
                    })
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        BaseButton(
            onClick = { viewModel.onLogoutClicked() },
            modifier = Modifier
                .fillMaxWidth()
                .background(shape = RoundedCornerShape(12.dp), color = dangerRed),
            style = ButtonStyle.PRIMARY,
            text = stringResource(id = R.string.button_logout),
            textStyle = TextStyle(
                fontFamily = nunitoSans,
                fontWeight = FontWeight.Light,
                fontSize = 16.sp
            ),
            containerColor = dangerRed,
            contentColor = Color.White,
            contentPadding = PaddingValues(vertical = 8.dp)
        )
    }
}