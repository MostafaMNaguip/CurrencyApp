package com.example.currencyapp.ui.base

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.currencyapp.utils.Constants
import com.example.currencyapp.utils.NetworkState
import com.example.currencyapp.utils.Utils.isInternetAvailable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() :
    ViewModel() {

    fun <T> runApi(
        _apiStateFlow: MutableStateFlow<NetworkState>,
        block: Response<T>
    ) {

        try {
            if (isInternetAvailable())
                runCatching {
                    block
                }.onFailure {

                    Log.e(TAG, "runApi: 3")
                    when (it) {
                        is java.net.UnknownHostException ->
                            _apiStateFlow.value =
                                NetworkState.Error(Constants.Codes.EXCEPTIONS_CODE)
                        is java.net.ConnectException ->
                            _apiStateFlow.value =
                                NetworkState.Error(Constants.Codes.EXCEPTIONS_CODE)
                        else -> _apiStateFlow.value =
                            NetworkState.Error(Constants.Codes.UNKNOWN_CODE)
                    }

                }.onSuccess {

                    Log.e(TAG, "runApi: 4")
                    if (it.body() != null)
                        _apiStateFlow.value = NetworkState.Result(it.body())
                    else {
                        Log.e(TAG, "runApi: ${it.errorBody()}")
                        _apiStateFlow.value = NetworkState.Error(Constants.Codes.UNKNOWN_CODE)
                    }
                }
            else
                _apiStateFlow.value = NetworkState.Error(Constants.Codes.EXCEPTIONS_CODE)
        } catch (e: Exception) {
            Log.e(TAG, "runApi: ${e.message}")
        }


    }

    companion object {
        private val TAG = this::class.java.name
    }

}