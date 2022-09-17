package com.example.covistat

import javax.inject.Inject

class Repository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getCountryList() = apiHelper.getCountryList()
    suspend fun getCountryInfo(country: String) = apiHelper.getCountryInfo(country)
}