package com.example.cryptoapp.data.worker

import android.content.Context
import android.util.Log
import androidx.work.*
import com.example.cryptoapp.data.database.AppDataBase
import com.example.cryptoapp.data.mapper.CoinMapper
import com.example.cryptoapp.data.network.ApiFactory
import kotlinx.coroutines.delay

class RefreshDataWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    private val coinInfoDao = AppDataBase.getInstance(context).coinInfoDao()
    private val mapper = CoinMapper()
    private val apiService = ApiFactory.apiServices

    override suspend fun doWork(): Result {
            while (true) {
                try {
                    val topCoins = apiService.getTopCoinsInfo(limit = 50)
                    Log.e("tagtag", topCoins.toString())
                    val fSyms = mapper.mapCoinNamesListToString(topCoins)
                    val jsonContainer = apiService.getFullPriceList(fSyms = fSyms)
                    val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
                    val dbModelList = coinInfoDtoList.map { mapper.mapDtoToDBModel(it) }
                    coinInfoDao.insertPriceList(dbModelList)
                } catch (e: Exception) {
                }
                delay(10000)
            }
    }

    companion object{
        const val NAME = "RefreshDataWorker"

        fun makeRequest(): OneTimeWorkRequest{
            return OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
        }
    }
}