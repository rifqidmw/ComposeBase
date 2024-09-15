package com.aigs.base.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aigs.base.R
import com.aigs.base.common.AppConstants.Route
import com.aigs.base.ui.components.BaseButton
import com.aigs.base.ui.components.BaseTextField
import com.aigs.base.ui.components.ButtonStyle
import com.aigs.base.ui.theme.ComposeBaseTheme
import com.aigs.base.ui.theme.NunitoSans
import com.aigs.base.ui.theme.PrimaryBlack
import com.aigs.base.ui.theme.PrimaryBlue
import com.aigs.base.ui.theme.Raleway
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun LoginView(navController: NavController, viewModel: LoginViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val navigationEvent by viewModel.navigationEvent.collectAsState()

    val systemController = rememberSystemUiController()

    DisposableEffect(systemController) {
        systemController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = true
        )
        onDispose {}
    }

    LaunchedEffect(navigationEvent) {
        when (navigationEvent) {
            is NavigationEvent.NavigateToHome -> {
                navController.navigate(Route.ONBOARDING)
                viewModel.onNavigationEventHandled()
            }

            else -> {}
        }
    }

    ComposeBaseTheme(enableEdgeToEdge = true) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.bg_login_screen),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Spacer(modifier = Modifier.height(80.dp))

                    Text(
                        text = "Login",
                        fontFamily = Raleway,
                        fontWeight = FontWeight.Bold,
                        fontSize = 52.sp,
                        letterSpacing = (-0.5).sp,
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )

                    Text(
                        text = "Good to see you back! ♥",
                        fontFamily = NunitoSans,
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp,
                        lineHeight = 35.sp,
                        color = PrimaryBlack,
                        modifier = Modifier.padding(top = 4.dp)
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    BaseTextField(
                        value = uiState.email,
                        onValueChange = { viewModel.onEmailChange(it) },
                        label = "Email",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier.fillMaxWidth()
                    )

                    val emailError = uiState.emailError
                    if (emailError != null) {
                        Text(
                            text = emailError,
                            fontFamily = NunitoSans,
                            fontWeight = FontWeight.Light,
                            fontSize = 12.sp,
                            color = Color.Red,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(36.dp))

                    Column {
                        BaseButton(
                            onClick = { viewModel.onNextClicked() },
                            modifier = Modifier.fillMaxWidth(),
                            style = ButtonStyle.PRIMARY,
                            text = "Next",
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
                            onClick = { navController.popBackStack() },
                            style = ButtonStyle.TEXT,
                            text = "Cancel",
                            textStyle = TextStyle(
                                fontFamily = NunitoSans,
                                fontWeight = FontWeight.Light,
                                fontSize = 15.sp,
                                lineHeight = 26.sp,
                                color = PrimaryBlack
                            ),
                            containerColor = Color.Transparent,
                            contentPadding = PaddingValues(horizontal = 8.dp),
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}
