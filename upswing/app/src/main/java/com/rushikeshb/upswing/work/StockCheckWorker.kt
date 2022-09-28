package com.rushikeshb.upswing.work

import android.content.Context
import androidx.annotation.MainThread
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import com.google.common.util.concurrent.ListenableFuture

class StockCheckWorker(private val context: Context, private val params: WorkerParameters)
    : ListenableWorker(context, params) {

    @MainThread
    override fun startWork(): ListenableFuture<Result> {
        val future = SettableFuture.create<Result>()
        val limit = params.inputData.getInt("price", 10)
        val s = StockPriceChecker()
        s.checkStockPrice(context, limit)
        future.set(Result.success())
        return future
    }
}
