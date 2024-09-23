package com.aigs.base.data.response

data class CountryResponse(
    val error: Boolean,
    val msg: String,
    val data: List<Country>
)

data class Country(
    val name: String,
    val flag: String,
    val iso2: String,
    val iso3: String
)