package com.aigs.base.data.remote

import com.aigs.base.common.AppConstants
import com.aigs.base.data.response.CountryResponse
import retrofit2.http.GET

interface CountriesService {
    @GET(AppConstants.Api.FLAGS)
    suspend fun getCountries(): CountryResponse
}