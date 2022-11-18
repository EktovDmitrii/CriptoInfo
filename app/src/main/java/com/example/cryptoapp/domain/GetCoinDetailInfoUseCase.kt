package com.example.cryptoapp.domain

import androidx.lifecycle.LiveData

class GetCoinDetailInfoUseCase(private val repository: CoinRepository) {
    operator fun invoke(fromSymbol: String) = repository.getCoinDetailInfo(fromSymbol)
}