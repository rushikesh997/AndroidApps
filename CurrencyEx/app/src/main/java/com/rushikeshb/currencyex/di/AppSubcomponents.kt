package com.rushikeshb.currencyex.di

import com.rushikeshb.currencyex.ui.di.RatesComponent
import dagger.Module


// This module tells a Component which are its subcomponents
@Module(
    subcomponents = [
        RatesComponent::class
    ]
)
class AppSubcomponents