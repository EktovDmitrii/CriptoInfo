package com.example.cryptoapp.di

import android.app.Application
import com.example.cryptoapp.data.database.AppDataBase
import com.example.cryptoapp.data.database.CoinInfoDao
import com.example.cryptoapp.data.database.CoinRepositoryImpl
import com.example.cryptoapp.data.network.ApiFactory
import com.example.cryptoapp.data.network.ApiServices
import com.example.cryptoapp.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {


    @Binds
    @ApplicationScope
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository


    companion object {


        @Provides
        @ApplicationScope
        fun provideCoinInfoDao(application: Application): CoinInfoDao {
            return AppDataBase.getInstance(application).coinInfoDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiServices(): ApiServices {
            return ApiFactory.apiServices
        }
    }
}