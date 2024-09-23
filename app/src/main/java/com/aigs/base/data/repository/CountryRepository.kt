package com.aigs.base.data.repository

import com.aigs.base.data.response.Country
import com.aigs.base.data.remote.CountriesService

class CountryRepository(private val api: CountriesService) {
    suspend fun getCountries(): List<Country> {
        return api.getCountries().data
    }
}