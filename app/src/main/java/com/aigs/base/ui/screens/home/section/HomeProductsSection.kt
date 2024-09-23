package com.aigs.base.ui.screens.home.section

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.aigs.base.R
import com.aigs.base.data.response.CategoriesResponse
import com.aigs.base.data.response.Product
import com.aigs.base.domain.model.CategoriesModel
import com.aigs.base.domain.model.ProductModel
import com.aigs.base.ui.theme.nunitoSans
import com.aigs.base.ui.theme.primaryBlack
import com.aigs.base.ui.theme.primaryBlue
import com.aigs.base.ui.theme.raleway

@Composable
fun HomeProductsSection(
    categories: List<CategoriesModel>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
    isLoading: Boolean,
    error: String?,
    products: List<ProductModel>
) {
    Column(modifier = Modifier.fillMaxSize()) {
        CategoryList(
            categories = categories.take(5),
            selectedCategory = selectedCategory,
            onCategorySelected = onCategorySelected
        )
        Spacer(modifier = Modifier.height(20.dp))
        when {
            isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = primaryBlue
                )
            }
            error != null -> {
                Text(
                    "Error: $error",
                    fontFamily = raleway,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    lineHeight = 10.sp,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            products.isEmpty() -> {
                Text(
                    stringResource(id = R.string.label_empty_product),
                    fontFamily = raleway,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    lineHeight = 10.sp,
                    color = primaryBlack,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            else -> {
                ProductGrid(products = products)
            }
        }
    }
}

@Composable
fun CategoryList(
    categories: List<CategoriesModel>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Text(
            text = stringResource(id = R.string.label_title_products),
            fontFamily = raleway,
            fontWeight = FontWeight.Bold,
            fontSize = 21.sp,
            lineHeight = 30.sp,
            color = primaryBlack,
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(categories) { category ->
                CategoryChip(
                    text = category.name,
                    isSelected = category.slug == selectedCategory,
                    onClick = { onCategorySelected(category.slug) }
                )
            }
        }
    }
}

@Composable
fun CategoryChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFE5EBFC))
                .clickable(onClick = onClick)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = text,
                fontFamily = raleway,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                lineHeight = 21.sp,
                color = primaryBlue,
            )
        }
        if (isSelected) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(Color.Green, CircleShape)
                    .align(Alignment.TopEnd)
            )
        }
    }
}

@Composable
fun ProductGrid(products: List<ProductModel>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        products.chunked(2).forEach { productPair ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                productPair.forEach { product ->
                    ProductCard(
                        product = product,
                        modifier = Modifier.weight(1f)
                    )
                }
                if (productPair.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ProductCard(product: ProductModel, modifier: Modifier) {
    Box(
        modifier = modifier
    ) {
        Column {
            Card(
                colors = CardDefaults.cardColors(Color.White),
                elevation = CardDefaults.cardElevation(1.dp)
            ) {
                AsyncImage(
                    model = product.images.first(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                text = product.title,
                fontFamily = nunitoSans,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                color = primaryBlack,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 11.dp)
            )
            Text(
                text = "$${product.price}",
                fontFamily = raleway,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                lineHeight = 21.sp,
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
    }
}