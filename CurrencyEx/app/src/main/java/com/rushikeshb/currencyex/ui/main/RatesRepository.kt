package com.rushikeshb.currencyex.ui.main

import com.rushikeshb.currencyex.data.model.RateApiResponse
import com.rushikeshb.currencyex.data.remote.RateService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RatesRepository @Inject constructor(private val rateService: RateService) {

    fun requestLatestRatesLiveData(base: String): Single<RateApiResponse> {
        return rateService.getLatestRatesByBase(base)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun requestExchangeRateLiveData(base: String, symbol: String): Single<RateApiResponse> {
        return rateService.getSpecificExchangeRate(base, symbol)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}