package com.example.cryptoapp.domain


data class CoinInfo(
    val price: Double?,
    val lowday: Double?,
    val highday: Double?,
    val lastmarket: String?,
    val fromsymbol: String,
    val tosymbol: String?,
    val lastupdate: Int?,
    val imageurl: String?
)