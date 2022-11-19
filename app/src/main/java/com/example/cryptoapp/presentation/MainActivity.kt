package com.example.cryptoapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.example.cryptoapp.domain.CoinInfo
import com.example.cryptoapp.presentation.adapter.CoinInfoAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    private val binding by lazy {
        ActivityCoinPriceListBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapter = CoinInfoAdapter(this)

        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinInfo) {
                if (isOnePaneMode()) {
launchActivity(coinPriceInfo.fromsymbol)
                }else {
                    launchFragment(coinPriceInfo.fromsymbol)
                }
            }
        }
        binding.rvCoinPriceList.adapter = adapter
        binding.rvCoinPriceList.itemAnimator = null
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.coinInfoList.observe(this) {
            adapter.submitList(it)
        }

    }

    private fun isOnePaneMode(): Boolean {
        return binding.coinDetailContainer == null
    }

    private fun launchFragment(fromSymbol: String) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.coin_detail_container, CoinDetailFragment.newInstance(fromSymbol))
            .addToBackStack(null)
            .commit()
    }

    private fun launchActivity(fromSymbol: String){
        val intent =
        CoinDetailActivity.newIntent(this@MainActivity, fromSymbol)
        startActivity(intent)

    }
}

