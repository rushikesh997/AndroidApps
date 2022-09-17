package com.example.covistat

import retrofit2.Response
import javax.inject.Inject

class CoronaHelperImpl @Inject constructor(private val service: CoronaService): ApiHelper {
    override suspend fun getCountryList(): Response<List<CountriesResponse>> =
        service.getCountryList()

    override suspend fun getCountryInfo(country: String): Response<CountriesResponse> =
        service.getCountryInfo(country)
}