package com.example.covistat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository,
    private val networkHelper: NetworkHelper
): ViewModel() {

    private val _countryData = MutableLiveData<Source<List<CountryResponse>>>()
    val countryData : LiveData<Source<List<CountryResponse>>> get() = _countryData

    init {
        getData()
    }

    override fun getData() {
        viewModelScope.launch {
            _countryData.postValue(Source.loading(null))

            if (networkHelper.isInternetAvailable()) {
                repository.getCountryList().let { response ->
                    if (response.isSuccessful) {
                        _countryData.postValue(Source.success(response.body()))
                    } else {
                        _countryData.postValue(Source.error(response.errorBody().toString(), null))
                    }
                }
            } else {
                _countryData.postValue(Source.error("Internet connection not found", null))
            }
        }
    }
}