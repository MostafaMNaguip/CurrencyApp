package com.example.currencyapp.ui.currencies_historical

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
class HistoricalDataViewModel@Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val apiEndPoints: ApiEndPoints
): BaseViewModel()
{

    private val _historicalDataStateFlow = MutableStateFlow<NetworkState>(NetworkState.Idle)
    val historicalDataStateFlow: MutableStateFlow<NetworkState> get() = _historicalDataStateFlow

    fun getHistoricalData(date: String,base: String,symbols: String) {

        _historicalDataStateFlow.value = NetworkState.Loading

        viewModelScope.launch(dispatcher) {
            runApi(
                _historicalDataStateFlow,
                apiEndPoints.getHistoricalDetails(apiKey = Constants.access_key
                ,date,base,symbols)
            )
        }

    }


}