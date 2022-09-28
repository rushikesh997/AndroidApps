package com.rushikeshb.currencyex.ui.di

import androidx.lifecycle.ViewModel
import com.rushikeshb.currencyex.di.ViewModelKey
import com.rushikeshb.currencyex.ui.main.RatesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class RatesModule {

    @Binds
    @IntoMap
    @ViewModelKey(RatesViewModel::class)
    abstract fun bindViewModel(viewModel: RatesViewModel): ViewModel
}
