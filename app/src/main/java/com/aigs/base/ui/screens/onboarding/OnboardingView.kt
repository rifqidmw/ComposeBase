package com.aigs.base.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aigs.base.R
import com.aigs.base.common.AppConstants.Route
import com.aigs.base.ui.components.BaseButton
import com.aigs.base.ui.components.ButtonStyle
import com.aigs.base.ui.theme.NunitoSans
import com.aigs.base.ui.theme.PrimaryBlack
import com.aigs.base.ui.theme.PrimaryBlue
import com.aigs.base.ui.theme.Raleway

@Composable
fun OnboardingView(navController: NavController, viewModel: OnboardingViewModel) {

    val navigationEvent by viewModel.navigationEvent.collectAsState()
    LaunchedEffect(navigationEvent) {
        when (navigationEvent) {
            is OnboardingNavigationEvent.NavigateToCreateAccount -> {
                navController.navigate(Route.CREATE_ACCOUNT)
                viewModel.onNavigationEventHandled()
            }

            is OnboardingNavigationEvent.NavigateToLogin -> {
                navController.navigate(Route.LOGIN)
                viewModel.onNavigationEventHandled()
            }

            else -> {}
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = "App Logo",
                    modifier = Modifier.size(140.dp)
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = stringResource(id = R.string.label_title_onboard),
                    fontFamily = Raleway,
                    fontWeight = FontWeight.Bold,
                    fontSize = 50.sp,
                    lineHeight = 54.sp,
                    letterSpacing = (-0.5).sp,
                    color = PrimaryBlack
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.label_subtitle_onboard),
                    color = PrimaryBlack,
                    fontFamily = NunitoSans,
                    fontWeight = FontWeight.Light,
                    fontSize = 19.sp,
                    lineHeight = 30.sp,
                    textAlign = TextAlign.Center,
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            BaseButton(
                onClick = { viewModel.onGetStartedClicked() },
                modifier = Modifier.fillMaxWidth(),
                style = ButtonStyle.PRIMARY,
                text = stringResource(id = R.string.button_register),
                textStyle = TextStyle(
                    fontFamily = NunitoSans,
                    fontWeight = FontWeight.Light,
                    fontSize = 20.sp
                ),
                containerColor = PrimaryBlue,
                contentColor = Color.White,
                contentPadding = PaddingValues(vertical = 16.dp)
            )

            Spacer(modifier = Modifier.height(18.dp))

            BaseButton(
                onClick = { viewModel.onLoginClicked() },
                style = ButtonStyle.TEXT,
                text = stringResource(id = R.string.button_login),
                textStyle = TextStyle(
                    fontFamily = NunitoSans,
                    fontWeight = FontWeight.Light,
                    fontSize = 15.sp,
                    lineHeight = 26.sp,
                    color = PrimaryBlack
                ),
                trailingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_forward),
                        contentDescription = "Forward"
                    )
                },
                containerColor = Color.Transparent,
                contentPadding = PaddingValues(horizontal = 8.dp),
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}