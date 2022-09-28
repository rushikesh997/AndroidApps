package com.rushikeshb.currencyex

import android.app.Application
import com.rushikeshb.currencyex.di.AppComponent
import com.rushikeshb.currencyex.di.DaggerAppComponent
import timber.log.Timber

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }

    val appComponent: AppComponent by lazy {
            DaggerAppComponent.factory().create(applicationContext)
    }
}
