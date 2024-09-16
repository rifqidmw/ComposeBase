package com.aigs.base.data.remote

import com.aigs.base.data.model.CountryResponse
import retrofit2.http.GET

interface CountriesService {
    @GET("countries/flag/images")
    suspend fun getCountries(): CountryResponse
}