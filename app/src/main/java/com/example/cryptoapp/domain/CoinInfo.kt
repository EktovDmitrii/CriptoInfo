package com.example.cryptoapp.domain

import javax.inject.Inject


data class CoinInfo @Inject constructor(
    val price: Double?,
    val lowday: Double?,
    val highday: Double?,
    val lastmarket: String?,
    val fromsymbol: String,
    val tosymbol: String?,
    val lastupdate: String?,
    val imageurl: String
)