package com.lhd.androidbase.ui.example_service

import android.annotation.SuppressLint
import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("SpecifyJobSchedulerIdRange")
class JobSchedulerService : JobService() {

    private val serviceJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + serviceJob)
    override fun onStartJob(p0: JobParameters?): Boolean {
        Toast.makeText(applicationContext, "Service running!", Toast.LENGTH_SHORT).show()

        coroutineScope.launch {
            doAsyncTask(p0)
        }

        return true
    }

    private suspend fun doAsyncTask(params: JobParameters?) {
//        for (i in 1..10) {
//            Log.e(TAG, "doAsyncTask:  $i")
//            delay(1000)
//        }
        Log.e(TAG, "doAsyncTask: abc")
        delay(15000)
        Log.e(TAG, "doAsyncTask: abc-done")
        jobFinished(params, false)
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        Log.e(TAG, "onStopJob: ")
        Toast.makeText(applicationContext, "Service stop!", Toast.LENGTH_SHORT).show()
        serviceJob.cancel()
        return true
    }


    companion object {
        const val TAG = "JobService"
        const val ID_JOB = 1
    }
}