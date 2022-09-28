package com.rushikeshb.fgsample

import android.app.Notification.FOREGROUND_SERVICE_IMMEDIATE
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat

class MyService: Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = this.applicationContext
                .getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(
                NotificationChannel("Low", "Low Priority", NotificationManager.IMPORTANCE_LOW))
        }

        val builder = NotificationCompat.Builder(this, "Low")
            .setContentTitle("FGS Android 13 test")
            .setContentText("FGS running")
            .setPriority(1)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setForegroundServiceBehavior(FOREGROUND_SERVICE_IMMEDIATE)

        val from = intent?.getStringExtra("from")
        if (from!! == "start") {
            startForeground(1, builder.build())
        } else {
            stopForeground(true)
        }
        return START_STICKY
    }
}
