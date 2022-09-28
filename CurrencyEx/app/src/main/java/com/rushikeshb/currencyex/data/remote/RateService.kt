package com.rushikeshb.currencyex.data.remote

import com.rushikeshb.currencyex.data.model.RateApiResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RateService {

    @GET("latest")
    fun getLatestRatesByBase(
        @Query("base") base : String
    ) : Single<RateApiResponse>

    @GET("latest")
    fun getSpecificExchangeRate(
        @Query("base") base: String,
        @Query("symbols") symbol: String
    ) : Single<RateApiResponse>
}