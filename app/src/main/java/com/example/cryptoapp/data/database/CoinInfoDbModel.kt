package com.example.cryptoapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

@Entity(tableName = "full_price_list")
data class CoinInfoDbModel @Inject constructor(
    @PrimaryKey
    val fromsymbol: String,
    val price: Double?,
    val lowday: Double?,
    val highday: Double?,
    val lastmarket: String?,
    val tosymbol: String?,
    val lastupdate: Int?,
    val imageurl: String
)