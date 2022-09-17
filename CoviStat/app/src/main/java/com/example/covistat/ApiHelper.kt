package com.example.covistat

import retrofit2.Response

interface ApiHelper {
    suspend fun getCountryList(): Response<List<CountriesResponse>>
    suspend fun getCountryInfo(country: String): Response<CountriesResponse>
}
