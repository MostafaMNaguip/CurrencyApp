package com.example.currencyapp.data.network

import com.example.currencyapp.data.pojo.ConvertCurrencyResponse
import com.example.currencyapp.data.pojo.CurrenciesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndPoints {

    @GET("symbols")
    suspend fun getCurrencies(
        @Header("apiKey") apiKey: String,
    ):Response<CurrenciesResponse>

    @GET("convert")
    suspend fun convertCurrency(
        @Header("apiKey") apiKey: String,
        @Query("to") to: String,
        @Query("from") from: String,
        @Query("amount") amount: String
    ):Response<ConvertCurrencyResponse>

    @GET("{date}")
    suspend fun getHistoricalDetails(
        @Header("apiKey") apiKey: String,
        @Path("date") date: String,
        @Query("base") base: String,
        @Query("symbols") symbols: String
    ):Response<ConvertCurrencyResponse>


}