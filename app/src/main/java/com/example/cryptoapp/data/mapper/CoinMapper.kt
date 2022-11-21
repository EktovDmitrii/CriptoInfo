package com.example.cryptoapp.data.mapper

import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import com.example.cryptoapp.data.database.CoinInfoDbModel
import com.example.cryptoapp.data.network.model.CoinInfoDto
import com.example.cryptoapp.data.network.model.CoinInfoJsonContainerDto
import com.example.cryptoapp.data.network.model.CoinNamesListDto
import com.example.cryptoapp.domain.CoinInfo
import com.google.gson.Gson
import java.sql.Timestamp
import java.util.*
import javax.inject.Inject

class CoinMapper @Inject constructor() {

    fun mapDtoToDBModel(dto: CoinInfoDto) = CoinInfoDbModel(
        fromsymbol = dto.fromsymbol,
        price = dto.price,
        tosymbol = dto.tosymbol,
        lowday = dto.lowday,
        highday = dto.highday,
        lastmarket = dto.lastmarket,
        imageurl = BASE_IMAGE_URL + dto.imageurl,
        lastupdate = dto.lastupdate
    )

    fun mapJsonContainerToListCoinInfo(jsonContainer: CoinInfoJsonContainerDto): List<CoinInfoDto> {
        val result = mutableListOf<CoinInfoDto>()
        val jsonObject = jsonContainer.json ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    fun mapCoinNamesListToString(namesListDto: CoinNamesListDto): String{
        return namesListDto.coinNameDto?.map {
            it.coinNameDto?.name }?.joinToString(",") ?: ""
    }

    fun mapDbModelToEntity(coinInfoDbModel: CoinInfoDbModel): CoinInfo = CoinInfo(
        fromsymbol = coinInfoDbModel.fromsymbol,
        price = coinInfoDbModel.price,
        tosymbol = coinInfoDbModel.tosymbol,
        lowday = coinInfoDbModel.lowday,
        highday = coinInfoDbModel.highday,
        lastmarket = coinInfoDbModel.lastmarket,
        imageurl = coinInfoDbModel.imageurl,
        lastupdate = convertTimestampToTime(coinInfoDbModel.lastupdate)
    )

   private fun convertTimestampToTime(timestamp: Int?):String{
        if (timestamp == null) return ""
        val stamp = Timestamp((timestamp * 1000).toLong())
        val date = Date(stamp.time)
        val pattern = "HH:mm:ss"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }

    companion object{
        const val BASE_IMAGE_URL = "https://cryptocompare.com/"
    }
}