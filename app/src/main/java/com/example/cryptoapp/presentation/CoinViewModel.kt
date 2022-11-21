package com.example.cryptoapp.presentation

import androidx.lifecycle.ViewModel
import com.example.cryptoapp.domain.GetCoinDetailInfoUseCase
import com.example.cryptoapp.domain.GetCoinInfoListUseCase
import com.example.cryptoapp.domain.LoadDataUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinDetailInfoUseCase: GetCoinDetailInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {


    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinDetailInfoUseCase(fSym)


    init {
        //viewModelScope.launch {
        loadDataUseCase()
        //}
    }
}








