package com.aigs.base.ui.screens.home.section

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aigs.base.R
import com.aigs.base.ui.theme.primaryBlack
import com.aigs.base.ui.theme.raleway

@Composable
fun HomeGreetingsSection(name: String) {
    Text(
        text = stringResource(id = R.string.label_greeting, name),
        fontFamily = raleway,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        color = primaryBlack,
        modifier = Modifier.padding(horizontal = 20.dp)
    )
}