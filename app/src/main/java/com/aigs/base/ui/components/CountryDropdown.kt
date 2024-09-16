package com.aigs.base.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.aigs.base.R
import com.aigs.base.data.model.Country
import com.aigs.base.ui.theme.NunitoSans
import com.aigs.base.ui.theme.PrimaryBlack

@Composable
fun CountryDropdown(
    countries: List<Country>,
    selectedCountry: Country?,
    onCountrySelected: (Country) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val defaultCountry = countries.find { it.iso2 == "ID" } ?: countries.firstOrNull()

    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()

    Box(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.clickable { expanded = true }
            ) {
                FlagImage(
                    flagUrl = selectedCountry?.flag ?: defaultCountry?.flag ?: "",
                    countryName = selectedCountry?.flag ?: defaultCountry?.name ?: "",
                    imageLoader = imageLoader
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_down),
                    contentDescription = "Select country",
                    modifier = Modifier.size(15.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Divider(
                color = PrimaryBlack,
                modifier = Modifier
                    .height(24.dp)
                    .width(1.dp)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .widthIn(max = 250.dp)
                .heightIn(max = 200.dp)
                .background(Color.White),
            offset = DpOffset(x = 0.dp, y = 20.dp)
        ) {
            countries.forEach { country ->
                DropdownMenuItem(
                    text = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            FlagImage(
                                flagUrl = country.flag,
                                countryName = country.name,
                                imageLoader = imageLoader
                            )
                            Text(
                                text = "${country.name} (${country.iso2})",
                                color = PrimaryBlack,
                                fontSize = 14.sp,
                                fontFamily = NunitoSans,
                                fontWeight = FontWeight.Medium,
                                lineHeight = 20.sp,
                            )
                        }
                    },
                    onClick = {
                        onCountrySelected(country)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun FlagImage(flagUrl: String, countryName: String, imageLoader: ImageLoader) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(flagUrl)
            .decoderFactory(SvgDecoder.Factory())
            .crossfade(true)
            .build(),
        contentDescription = "$countryName flag",
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .width(25.dp)
            .height(15.dp),
        imageLoader = imageLoader
    )
}