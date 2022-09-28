package com.rushikeshb.currencyex.data.model

data class PastApiResponse (val base: String,
                            val endAt: String,
                            val rates: Map<String, Map<String, Double>>,
                            val startAt: String)