package com.aigs.base.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.aigs.base.R
import com.aigs.base.common.AppConstants.Route
import com.aigs.base.data.model.Product
import com.aigs.base.data.model.ProductResponse
import com.aigs.base.ui.components.BaseTextField
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

        when {
            uiState.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            uiState.error != null -> Text("Error: ${uiState.error}", color = Color.Red)
            else -> ProductList(products = uiState.products)
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
            .padding(start = 16.dp, end = 16.dp, top = 24.dp),
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

@Composable
fun ProductList(products: List<Product>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(products) { product ->
            ProductCard(product)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    Card(
        colors = CardDefaults.cardColors(Color.Transparent),
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Card(
                modifier = Modifier
                    .size(width = 120.dp, height = 260.dp)
                    .padding(8.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                AsyncImage(
                    model = product.images.first(),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 10.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = product.title,
                        fontFamily = raleway,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        color = primaryBlack,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "$${product.price}",
                        fontFamily = raleway,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        lineHeight = 22.sp,
                        color = primaryBlack
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .background(
                                color = Color(0xFFE5EBFC), shape = RoundedCornerShape(6.dp)
                            )
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = product.category,
                            fontFamily = raleway,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            lineHeight = 18.sp,
                            color = primaryBlack
                        )
                    }
                }
                Image(
                    painter = painterResource(id = R.drawable.ic_add_cart),
                    contentDescription = null,
                    modifier = Modifier
                        .align(
                            Alignment.BottomEnd
                        )
                        .padding(bottom = 16.dp)
                )
            }
        }
    }
}