package com.rushikeshb.upswing.work

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.rushikeshb.upswing.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext

class StockPriceChecker {

    private val context = newSingleThreadContext("StockPriceFetcher")
    private val scope = CoroutineScope(context)

    fun checkStockPrice(context: Context, limit: Int) {
        scope.launch {
            val stockPrice = 10 //TODO: make network call
            if (stockPrice < limit) {
                notifyUser(context)
            }
        }
    }

    private fun notifyUser(context: Context) {
        val notificationChannel = NotificationChannel("stock_price",
            "Stock Price Check",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)

        val notification =  Notification.Builder(context, "stock_price")
            .setContentTitle("Stock price fall")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentText("Your stock price has fallen below the limit")
            .build()

        notificationManager.notify(1, notification)
    }
}
