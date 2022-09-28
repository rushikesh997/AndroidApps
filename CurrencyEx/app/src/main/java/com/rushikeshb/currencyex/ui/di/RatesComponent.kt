package com.rushikeshb.currencyex.ui.di

import com.rushikeshb.currencyex.ui.main.MainActivity
import dagger.Subcomponent

// Definition of a Dagger subcomponent
@Subcomponent(modules = [RatesModule::class])
interface RatesComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): RatesComponent
    }

    fun inject(activity: MainActivity)
}
