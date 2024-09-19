package com.aigs.base.ui.screens.createaccount

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.aigs.base.ui.components.CountryDropdown
import com.aigs.base.ui.theme.ComposeBaseTheme
import com.aigs.base.ui.theme.nunitoSans
import com.aigs.base.ui.theme.primaryBlack
import com.aigs.base.ui.theme.primaryBlue
import com.aigs.base.ui.theme.raleway
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun CreateAccountView(navController: NavController, viewModel: CreateAccountViewModel) {
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
            is CreateAccountNavigationEvent.NavigateToLogin -> {
                navController.navigate(Route.LOGIN)
                viewModel.onNavigationEventHandled()
            }

            else -> {}
        }
    }

    ComposeBaseTheme(enableEdgeToEdge = true) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.bg_create_account_screen),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(modifier = Modifier.height(80.dp))

                Text(
                    text = stringResource(id = R.string.label_title_register),
                    fontFamily = raleway,
                    fontWeight = FontWeight.Bold,
                    fontSize = 52.sp,
                    letterSpacing = (-0.5).sp,
                    color = primaryBlack,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(start = 10.dp)
                )

                Spacer(modifier = Modifier.height(64.dp))

                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.Start)
                        .padding(start = 10.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_camera_placeholder),
                        contentDescription = "Add profile picture",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                    // Add click listener to open image picker
                }

                Spacer(modifier = Modifier.height(32.dp))

                BaseTextField(
                    value = uiState.email,
                    onValueChange = { viewModel.onEmailChange(it) },
                    label = stringResource(id = R.string.hint_email),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth(),
                    error = uiState.emailError
                )

                Spacer(modifier = Modifier.height(8.dp))

                BaseTextField(
                    value = uiState.password,
                    onValueChange = { viewModel.onPasswordChange(it) },
                    label = stringResource(id = R.string.hint_password),
                    isPassword = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth(),
                    error = uiState.passwordError
                )

                Spacer(modifier = Modifier.height(8.dp))

                BaseTextField(
                    value = uiState.phoneNumber,
                    isNumeric = true,
                    onValueChange = { viewModel.onPhoneNumberChange(it) },
                    label = stringResource(id = R.string.hint_phone_number),
                    leadingIcon = {
                        CountryDropdown(
                            countries = uiState.countries,
                            selectedCountry = uiState.selectedCountry,
                            onCountrySelected = { viewModel.onCountrySelected(it) },
                            isLoading = uiState.isLoading
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    error = uiState.phoneNumberError
                )

                Spacer(modifier = Modifier.weight(1f))

                Column {
                    BaseButton(
                        onClick = { viewModel.onDoneClick() },
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

                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}