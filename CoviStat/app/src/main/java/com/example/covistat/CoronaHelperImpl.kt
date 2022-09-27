package com.example.covistat

import retrofit2.Response
import javax.inject.Inject

class CoronaHelperImpl @Inject constructor(private val service: CoronaService): ApiHelper {
    override suspend fun getCountryList(): Response<List<CountryResponse>> =
        service.getCountryList()

    override suspend fun getCountryInfo(country: String): Response<CountryResponse> =
        service.getCountryInfo(country)
}