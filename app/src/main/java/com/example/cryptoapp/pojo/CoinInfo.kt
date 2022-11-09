package com.example.cryptoapp.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName as SerializedName1

data class CoinInfo (

    @SerializedName1("Name")
    @Expose
    val name: String? = null


)