package com.example.covistat

import androidx.lifecycle.ViewModel

abstract class ViewModel: ViewModel() {
    abstract fun getData()
}