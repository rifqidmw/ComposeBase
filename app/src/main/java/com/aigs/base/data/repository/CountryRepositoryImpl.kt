package com.aigs.base.data.repository

import com.aigs.base.data.response.Country
import com.aigs.base.data.remote.CountriesService
import com.aigs.base.domain.repository.CountryRepository

class CountryRepositoryImpl(private val api: CountriesService) : CountryRepository {
    override suspend fun getCountries(): List<Country> {
        return api.getCountries().data
    }
}