package com.rushikeshb.currencyex.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rushikeshb.currencyex.MyApplication

import com.rushikeshb.currencyex.databinding.ActivityMainBinding
import com.rushikeshb.currencyex.utils.Constants.FROM_CURRENCY
import com.rushikeshb.currencyex.utils.Constants.TO_CURRENCY
import com.rushikeshb.currencyex.utils.NetworkUtils.isNetworkAvailable
import com.rushikeshb.currencyex.utils.RateUtils.getCodeName
import com.rushikeshb.currencyex.utils.RateUtils.getFlag
import timber.log.Timber
import javax.inject.Inject

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var ratesAdapter: RatesAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val ratesViewModel by viewModels<RatesViewModel> { viewModelFactory }

    private var fromCurrency = FROM_CURRENCY
    private var toCurrency = TO_CURRENCY

    private var isSwitched = true

    override fun onCreate(savedInstanceState: Bundle?) {

        (application as MyApplication).appComponent.ratesComponent().create().inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initViews()

        setupObservers()

        loadAllRates(FROM_CURRENCY)

        loadDefaultRate()

        binding.imgConvert.setOnClickListener {
            exchangeRate()
        }
    }

    private fun initViews() {
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
            false)
        binding.rcRatesList.layoutManager = linearLayoutManager
        binding.rcRatesList.setHasFixedSize(true)

        ratesAdapter = RatesAdapter()
        binding.rcRatesList.adapter = ratesAdapter

    }

    private fun setupObservers() {
        ratesViewModel.getLatestRates().observe(this ) {
            ratesAdapter.submitList(it)
            ratesAdapter.notifyDataSetChanged()
        }

        ratesViewModel.getExchangeRate().observe(this) {
            Timber.tag(TAG).d("$fromCurrency to $toCurrency = ${it[0].second}")
            binding.txtCurUnitTo.text = String.format("%.4f", it[0].second)
        }

        ratesViewModel.dataLoading.observe(this) { isDataLoaded ->
            if (isDataLoaded) {
                binding.loadingIndicator.visibility = View.VISIBLE
            } else {
                binding.loadingIndicator.visibility = View.INVISIBLE
            }
        }

    }

    private fun loadAllRates(base: String) {
        if (!isNetworkAvailable(this)) {
            Toast.makeText(applicationContext, "Check Internet Connection", Toast.LENGTH_LONG).show()
        }else {
            ratesViewModel.requestLatestRates(base)
        }
    }

    private fun loadDefaultRate() {
        getRate(fromCurrency, toCurrency)

        updateExchangeRateUi(fromCurrency, toCurrency)
    }

    private fun getRate(from:String, to:String) {
        if (from == to) {
            binding.txtCurUnitTo.text = "???"
        } else {
            ratesViewModel.requestExchangeRates(from, to)
        }
    }

    private fun updateExchangeRateUi(baseCurrency:String, convertedToCurrency: String){
        binding.imgCurFlagFrom.setImageResource(getFlag(baseCurrency))
        binding.txtCurFromSc.text = baseCurrency
        binding.txtCurFrom.text = getCodeName(baseCurrency)
        binding.txtCurUnitFrom.text = "1"

        binding.imgCurFlagTo.setImageResource(getFlag(convertedToCurrency))
        binding.txtCurToSc.text = convertedToCurrency
        binding.txtCurTo.text = getCodeName(convertedToCurrency)
    }

    private fun exchangeRate() {
        if(isSwitched) {

            getRate(toCurrency, fromCurrency)

            loadAllRates(toCurrency)

            updateExchangeRateUi(toCurrency, fromCurrency)

            isSwitched = false
        }else {

            getRate(fromCurrency, toCurrency)

            loadAllRates(fromCurrency)

            updateExchangeRateUi(fromCurrency, toCurrency)

            isSwitched = true
        }
    }
}
