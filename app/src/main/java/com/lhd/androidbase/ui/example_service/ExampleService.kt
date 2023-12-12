package com.lhd.androidbase.ui.example_service

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class ExampleService(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        return try {

            Log.e(TAG, "doWork: start work")

            for (i in 1..10) {
                delay(1000)
                Log.e(TAG, "doWork: start work $i")
            }
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }

    companion object {
        const val TAG = "ExampleService"
    }

}