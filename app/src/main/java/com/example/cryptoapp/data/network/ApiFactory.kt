package com.example.cryptoapp.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private const val BASE_URL = "https://min-api.cryptocompare.com/data/"
     const val BASE_IMAGE_URL = "https://cryptocompare.com/"

    private val retrofit by lazy {
        Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
    }
    val  apiServices = retrofit.create(ApiServices::class.java)
}