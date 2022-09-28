package com.rushikeshb.upswing

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import com.rushikeshb.upswing.databinding.ActivityMainBinding
import com.rushikeshb.upswing.work.StockCheckWorker
import java.time.Duration

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.price.hint = "Enter stock price"
        binding.savePrice.setOnClickListener {
            scheduleWorker(binding.price.text.toString().toInt())
        }
    }

    private fun scheduleWorker(price: Int) {
       val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.UNMETERED).build()
       val data = Data.Builder().putInt("price", price).build()
       val periodicWork = PeriodicWorkRequestBuilder<StockCheckWorker>(Duration.ofMinutes(15))
           .setConstraints(constraints)
           .setInputData(data)
           .setInitialDelay(Duration.ZERO)
           .build()
        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork("stockPriceCheck",
                ExistingPeriodicWorkPolicy.REPLACE, periodicWork)
    }
}
