package com.example.cryptoapp.domain

import androidx.lifecycle.LiveData

class getCoinDetailInfoUseCase(private val repository: CoinRepository) {
    operator fun invoke(fromSymbol: String) = repository.getCoinDetailInfo(fromSymbol)
}