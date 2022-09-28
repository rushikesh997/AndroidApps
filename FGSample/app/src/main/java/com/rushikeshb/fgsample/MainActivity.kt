package com.rushikeshb.fgsample

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rushikeshb.fgsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun startService(v: View?) {
        startService(Intent(applicationContext, MyService::class.java).putExtra("from", "start"))
    }

    fun stopService(v: View?) {
        startService(Intent(applicationContext, MyService::class.java).putExtra("from", "stop"))
    }
}