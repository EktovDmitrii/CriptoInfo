package com.example.cryptoapp.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.ActivityCoinDetailBinding
import com.example.cryptoapp.databinding.FragmentCoinDetailBinding
import com.squareup.picasso.Picasso
import java.lang.RuntimeException

class CoinDetailFragment : Fragment() {

    private var _binding: FragmentCoinDetailBinding? = null
   private val binding: FragmentCoinDetailBinding
    get() = _binding ?: throw RuntimeException("FragmentCoinDetailBinding is null")



    private lateinit var viewModel: CoinViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCoinDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fromSymbol = getSymbol()
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.getDetailInfo(fromSymbol.toString()).observe(viewLifecycleOwner) {
            with(binding) {
                tvAveragePriceValue.text = it.price.toString()
                tvMinPricePerDay.text = it.lowday.toString()
                tvMaxPricePerDay.text = it.highday.toString()
                tvLastDealValue.text = it.lastmarket.toString()
                tvLastUpdate.text = it.lastupdate
                tvFromSymbol.text = it.fromsymbol
                tvToSymbol.text = it.tosymbol
                Picasso.get().load(it.imageurl).into(ivLogoCoin)
            }

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getSymbol(): String{
        return requireArguments().getString(EXTRA_FROM_SYMBOL, "")
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fsym"
        private const val EMPTY_SMBL = ""

        fun newInstance(fromSymbol: String): Fragment {
return CoinDetailFragment().apply {
arguments = Bundle().apply {
    putString(EXTRA_FROM_SYMBOL, fromSymbol)
}
}
        }
    }
}