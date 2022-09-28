package com.rushikeshb.currencyex.data.model

data class RateApiResponse(val rates: Map<String, Double>,
                           val base: String,
                           val date: String)
