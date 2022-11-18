package com.example.cryptoapp.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.R
import com.example.cryptoapp.data.network.ApiFactory.BASE_IMAGE_URL
import com.example.cryptoapp.utils.convertTimestampToTime
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_coin_detail.*

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL) ?: "EMPTY_SMBL"
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.getDetailInfo(fromSymbol.toString()).observe(this) {
            tvAveragePriceValue.text = it.price.toString()
            tvMinPricePerDay.text = it.lowday.toString()
            tvMaxPricePerDay.text = it.highday.toString()
            tvLastDealValue.text = it.lastmarket.toString()
            tvLastUpdate.text = convertTimestampToTime(it.lastupdate)
            tvFromSymbol.text = it.fromsymbol
            tvToSymbol.text = it.tosymbol
            Picasso.get().load(BASE_IMAGE_URL + it.imageurl).into(ivLogoCoin)

        }
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fsym"
        private const val EMPTY_SMBL = ""

        fun newIntent(context: Context, fromSymbol: String): Intent {
val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol )
            return intent
        }
    }
}