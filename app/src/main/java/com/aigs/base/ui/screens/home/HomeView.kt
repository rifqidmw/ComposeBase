package com.aigs.base.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aigs.base.R
import com.aigs.base.common.AppConstants.Route
import com.aigs.base.ui.components.BaseTextField
import com.aigs.base.ui.screens.home.section.HomeAnnouncementSection
import com.aigs.base.ui.screens.home.section.HomeGreetingsSection
import com.aigs.base.ui.screens.home.section.HomeProductsSection
import com.aigs.base.ui.theme.primaryBlack
import com.aigs.base.ui.theme.primaryBlue
import com.aigs.base.ui.theme.primaryGray
import com.aigs.base.ui.theme.raleway

@Composable
fun HomeView(navController: NavController, viewModel: HomeViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val navigationEvent by viewModel.navigationEvent.collectAsState()

    LaunchedEffect(navigationEvent) {
        when (navigationEvent) {
            is HomeNavigationEvent.NavigateToSettings -> {
                navController.navigate(Route.SETTINGS)
                viewModel.onNavigationEventHandled()
            }

            else -> {}
        }

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        AppBar(
            searchQuery = uiState.searchQuery,
            onSearchQueryChange = { viewModel.onSearchQueryChange(it) },
            onSettingsClicked = { viewModel.onSettingsClicked() },
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(20.dp))
                HomeGreetingsSection(name = uiState.authName)
                Spacer(modifier = Modifier.height(12.dp))
                HomeAnnouncementSection()
                Spacer(modifier = Modifier.height(20.dp))
                HomeProductsSection(
                    categories = uiState.categories,
                    selectedCategory = uiState.selectedCategory,
                    onCategorySelected = { viewModel.onCategorySelected(it) },
                    isLoading = uiState.isLoading,
                    error = uiState.error,
                    products = uiState.products
                )
            }
        }
    }
}

@Composable
fun AppBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onSettingsClicked: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Shop",
            fontFamily = raleway,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            lineHeight = 36.sp,
            color = primaryBlack,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        BaseTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            label = stringResource(id = R.string.hint_search),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Camera",
                    tint = primaryBlue
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .weight(1f)
                .height(40.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(
            onClick = onSettingsClicked,
            modifier = Modifier
                .size(40.dp)
                .background(color = primaryGray, shape = CircleShape)
                .clip(CircleShape)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_settings),
                contentDescription = "Settings",
                tint = primaryBlue
            )
        }
    }
}