package com.rushikeshb.currencyex.utils

import com.rushikeshb.currencyex.R

object RateUtils {

    fun getCodeName(key: String): String {
        return when (key) {
            "EUR" -> "Euro"
            "USD" -> "US Dollar"
            "CAD" -> "Canadian dollar"
            "INR" -> "Indian Rupee"
            "JPY" -> "Japanese yen"
            "CNY" -> "Chinese Yuan Renminbi"
            else -> "None"
        }
    }

    fun getFlag(key: String): Int {
        return when (key) {
            "EUR" -> R.drawable.flag_eur
            "USD" -> R.drawable.flag_usd
            "CAD" -> R.drawable.flag_cad
            "INR" -> R.drawable.flag_inr
            "JPY" -> R.drawable.flag_jpy
            "CNY" -> R.drawable.flag_cny
            else -> R.drawable.ic_money
        }
    }
}