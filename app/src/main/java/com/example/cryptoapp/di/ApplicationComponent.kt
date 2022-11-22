package com.example.cryptoapp.di

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.example.cryptoapp.presentation.CoinApp
import com.example.cryptoapp.presentation.CoinDetailFragment
import com.example.cryptoapp.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component


@ApplicationScope
@Component(
    modules = [DataModule::class, ViewModelModule::class, WorkerModule::class]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(application: CoinApp)

    fun inject(fragment: CoinDetailFragment)

    @Component.Factory
    interface Factory{
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}