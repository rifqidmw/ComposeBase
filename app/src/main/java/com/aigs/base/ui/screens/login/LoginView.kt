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
import androidx.compose.ui.res.stringResource
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
import com.aigs.base.ui.theme.nunitoSans
import com.aigs.base.ui.theme.primaryBlack
import com.aigs.base.ui.theme.primaryBlue
import com.aigs.base.ui.theme.raleway
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
            is LoginNavigationEvent.NavigateToHome -> {
                navController.navigate(Route.HOME)
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
                        .weight(1.5f)
                        .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Spacer(modifier = Modifier.height(80.dp))

                    Text(
                        text = stringResource(id = R.string.label_title_login),
                        fontFamily = raleway,
                        fontWeight = FontWeight.Bold,
                        fontSize = 52.sp,
                        letterSpacing = (-0.5).sp,
                        color = primaryBlack,
                        textAlign = TextAlign.Start
                    )

                    Text(
                        text = stringResource(id = R.string.label_subtitle_login),
                        fontFamily = nunitoSans,
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp,
                        lineHeight = 35.sp,
                        color = primaryBlack,
                        modifier = Modifier.padding(top = 4.dp)
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    BaseTextField(
                        value = uiState.username,
                        onValueChange = { viewModel.onUsernameChange(it) },
                        label = stringResource(id = R.string.hint_username),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier.fillMaxWidth(),
                        error = uiState.usernameError
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    
                    BaseTextField(
                        value = uiState.password,
                        onValueChange = { viewModel.onPasswordChange(it) },
                        isPassword = true,
                        label = stringResource(id = R.string.hint_password),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        modifier = Modifier.fillMaxWidth(),
                        error = uiState.passwordError
                    )

                    if (uiState.error != null) {
                        Text(
                            text = uiState.error!!,
                            fontFamily = nunitoSans,
                            fontWeight = FontWeight.Light,
                            fontSize = 12.sp,
                            color = Color.Red,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Column {
                        BaseButton(
                            onClick = { viewModel.onNextClicked() },
                            modifier = Modifier.fillMaxWidth(),
                            style = ButtonStyle.PRIMARY,
                            text = stringResource(id = R.string.button_next),
                            textStyle = TextStyle(
                                fontFamily = nunitoSans,
                                fontWeight = FontWeight.Light,
                                fontSize = 20.sp
                            ),
                            containerColor = primaryBlue,
                            contentColor = Color.White,
                            contentPadding = PaddingValues(vertical = 16.dp)
                        )
                        Spacer(modifier = Modifier.height(18.dp))
                        BaseButton(
                            onClick = { navController.popBackStack() },
                            style = ButtonStyle.TEXT,
                            text = stringResource(id = R.string.button_cancel),
                            textStyle = TextStyle(
                                fontFamily = nunitoSans,
                                fontWeight = FontWeight.Light,
                                fontSize = 15.sp,
                                lineHeight = 26.sp,
                                color = primaryBlack
                            ),
                            containerColor = Color.Transparent,
                            contentPadding = PaddingValues(horizontal = 8.dp),
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(36.dp))
                    }
                }
            }
        }
    }
}
