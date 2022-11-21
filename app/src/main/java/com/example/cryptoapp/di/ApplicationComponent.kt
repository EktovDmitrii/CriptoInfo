package com.example.cryptoapp.di

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.example.cryptoapp.presentation.CoinDetailFragment
import com.example.cryptoapp.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component


@Component(
    modules = [DataModule::class, ViewModelModule::class]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: CoinDetailFragment)

    @Component.Factory
    interface Factory{
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}