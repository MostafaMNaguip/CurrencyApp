package com.example.currencyapp.ui.convert_currency

import androidx.lifecycle.viewModelScope
import com.example.currencyapp.data.network.ApiEndPoints
import com.example.currencyapp.ui.base.BaseViewModel
import com.example.currencyapp.utils.Constants
import com.example.currencyapp.utils.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConvertCurrencyViewModel@Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val apiEndPoints: ApiEndPoints
): BaseViewModel()
{

    private val _currencyStateFlow = MutableStateFlow<NetworkState>(NetworkState.Idle)
    val currencyStateFlow: MutableStateFlow<NetworkState> get() = _currencyStateFlow

    private val _convertCurrencyStateFlow = MutableStateFlow<NetworkState>(NetworkState.Idle)
    val convertCurrencyStateFlow: MutableStateFlow<NetworkState> get() = _convertCurrencyStateFlow

    fun getCurrencies() {

        _currencyStateFlow.value = NetworkState.Loading

        viewModelScope.launch(dispatcher) {
            runApi(
                _currencyStateFlow,
                apiEndPoints.getCurrencies(apiKey = Constants.access_key)
            )
        }

    }

    fun convertCurrencies(from: String,to: String,amount: String) {

        _convertCurrencyStateFlow.value = NetworkState.Loading

        viewModelScope.launch(dispatcher) {
            runApi(
                _convertCurrencyStateFlow,
                apiEndPoints.convertCurrency(apiKey = Constants.access_key,
                    to,from,amount
                    )
            )
        }

    }

}