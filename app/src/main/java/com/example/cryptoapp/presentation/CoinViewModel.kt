package com.example.cryptoapp.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cryptoapp.data.network.ApiFactory
import com.example.cryptoapp.data.database.AppDataBase
import com.example.cryptoapp.data.network.model.CoinInfoDto
import com.example.cryptoapp.data.network.model.CoinInfoJsonContainerDto
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDataBase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    val priceList = db.coinPriceInfoDao().getPriceList()

    fun getDetailInfo(fSym: String): LiveData<CoinInfoDto>{
        return db.coinPriceInfoDao().getPriceInfoAboutCoin(fSym)
    }

    init {
        loadData()
    }



   private fun loadData() {
        val disposable = ApiFactory.apiServices.getTopCoinsInfo(limit = 50)
            .map { it.coinNameContainers?.map { it.coinName?.name }?.joinToString(",") }
            .flatMap { ApiFactory.apiServices.getFullPriceList(fSyms = it) }
            .map { getPriceListFromRowData(it) }
            //обновляем данные раз в десять секунд
                .delaySubscription(10, TimeUnit.SECONDS)
                //повторяем постоянные обновления данных
                .repeat()
                //повторяем обновление данных, после ошибки
                .retry()
           //RXjava
            .subscribeOn(Schedulers.io())
            .subscribe({
                db.coinPriceInfoDao().insertPriceList(it)
                Log.d("TEST_OF_LOADING_DATA", "Success: $it")
            }, {
                Log.d("TEST_OF_LOADING_DATA", "Failure: ${it.message.toString()}")
            })
        compositeDisposable.add(disposable)
    }




    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}