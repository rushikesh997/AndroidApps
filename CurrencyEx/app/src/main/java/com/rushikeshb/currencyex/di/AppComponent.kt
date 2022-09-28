package com.rushikeshb.currencyex.di

import android.content.Context
import com.rushikeshb.currencyex.ui.di.RatesComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelBuilderModule::class,
        AppSubcomponents::class,
        NetworkModule::class
    ]
)
interface AppComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }


    // Types that can be retrieved from the graph
    fun ratesComponent(): RatesComponent.Factory
}