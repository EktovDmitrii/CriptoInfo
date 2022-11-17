package com.example.cryptoapp.domain

import androidx.lifecycle.LiveData

interface CoinRepository {

    fun getCoinInfoList(): LiveData<List<CoinInfo>>

    fun getCoinDetailInfo(fromSymbol: String): LiveData<CoinInfo>

}