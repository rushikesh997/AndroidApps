package com.example.covistat

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CoronaService {

    @GET("countries/?sort=country")
    suspend fun getCountryList(): Response<List<CountriesResponse>>

    @GET("countries/{country}")
    suspend fun getCountryInfo(
        @Path("country") country: String
    ): Response<CountriesResponse>
}
