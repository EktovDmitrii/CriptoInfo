package com.example.cryptoapp.data.mapper

import com.example.cryptoapp.data.database.CoinInfoDbModel
import com.example.cryptoapp.data.network.model.CoinInfoDto
import com.example.cryptoapp.data.network.model.CoinInfoJsonContainerDto
import com.example.cryptoapp.data.network.model.CoinNamesListDto
import com.example.cryptoapp.domain.CoinInfo
import com.google.gson.Gson

class CoinMapper {

    fun mapDtoToDBModel(dto: CoinInfoDto): CoinInfoDbModel = CoinInfoDbModel(
        fromsymbol = dto.fromsymbol,
        price = dto.price,
        tosymbol = dto.tosymbol,
        lowday = dto.lowday,
        highday = dto.highday,
        lastmarket = dto.lastmarket,
        imageurl = dto.imageurl,
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
        lastupdate = coinInfoDbModel.lastupdate
    )
}