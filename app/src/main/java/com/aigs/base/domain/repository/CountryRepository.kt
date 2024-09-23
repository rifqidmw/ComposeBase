package com.aigs.base.domain.repository

import com.aigs.base.data.response.Country

interface CountryRepository {
    suspend fun getCountries() : List<Country>
}