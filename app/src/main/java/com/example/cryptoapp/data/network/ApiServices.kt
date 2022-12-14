package com.example.cryptoapp.data.network

import com.example.cryptoapp.data.model.CoinInfoListOfData
import com.example.cryptoapp.data.model.CoinPriceInfoRowData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("top/totalvolfull")
    fun getTopCoinsInfo(

        @Query(QUERY_PARAM_API_KEY) apiKey: String = "59629e946241bf7f59287a93e0923dbe17b219ee10a1b2ebdb40421602e94446",
        @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY

    ): Single<CoinInfoListOfData>

    @GET("pricemultifull")
    fun getFullPriceList(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "59629e946241bf7f59287a93e0923dbe17b219ee10a1b2ebdb40421602e94446",
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms: String = CURRENCY,
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSyms: String

    ): Single<CoinPriceInfoRowData>

    companion object {
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val CURRENCY = "USD"
    }
}