package com.example.covistat

import retrofit2.Response

interface ApiHelper {
    suspend fun getCountryList(): Response<List<CountryResponse>>
    suspend fun getCountryInfo(country: String): Response<CountryResponse>
}
