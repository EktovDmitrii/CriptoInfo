package com.example.cryptoapp.domain

import androidx.lifecycle.LiveData

class getCoinInfoListUseCase(private val repository: CoinRepository) {
    operator fun invoke() = repository.getCoinInfoList()

}