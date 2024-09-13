package com.aigs.base.presentation.onboarding

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aigs.base.R
import com.aigs.base.ui.theme.*
import com.aigs.base.ui.theme.Raleway

@Composable
fun OnboardingScreen(navController: NavController, viewModel: OnboardingViewModel = viewModel()) {
    val context = LocalContext.current
    val toastMessage by viewModel.showToast.collectAsState()

    toastMessage?.let {
        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        viewModel.onToastShown()
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
                    text = "Shoppe",
                    fontFamily = Raleway,
                    fontWeight = FontWeight.Bold,
                    fontSize = 50.sp,
                    lineHeight = 54.sp,
                    letterSpacing = (-0.5).sp,
                    color = PrimaryBlack
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Beautiful eCommerce UI Kit\nfor your online store",
                    color = PrimaryBlack,
                    fontFamily = NunitoSans,
                    fontWeight = FontWeight.Light,
                    fontSize = 19.sp,
                    lineHeight = 30.sp,
                    textAlign = TextAlign.Center,
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { viewModel.onGetStartedClicked() },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                Text(
                    text = "Let's get started",
                    color = Color.White,
                    fontFamily = NunitoSans,
                    fontWeight = FontWeight.Light,
                    fontSize = 20.sp,
                )
            }
            Spacer(modifier = Modifier.height(18.dp))

            Button(
                onClick = { viewModel.onGetStartedClicked() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            ) {
                Row(
                    modifier = Modifier.wrapContentWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "I already have an account",
                        color = Color(0xFF202020),
                        fontFamily = NunitoSans,
                        fontWeight = FontWeight.Light,
                        fontSize = 15.sp,
                        lineHeight = 26.sp
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_forward),
                        contentDescription = "Forward"
                    )
                }
            }
        }
    }
}