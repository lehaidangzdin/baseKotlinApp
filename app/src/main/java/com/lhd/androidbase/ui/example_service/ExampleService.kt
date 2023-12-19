package com.lhd.androidbase.ui.example_service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.navigation.NavDeepLinkBuilder
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.lhd.androidbase.R
import com.lhd.androidbase.activities.MainActivity
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@HiltWorker
class ExampleService @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    private val workerScope = CoroutineScope(Dispatchers.Default + SupervisorJob())
    override suspend fun doWork(): Result {
        return try {

            Log.e(TAG, "doWork: start work")

            for (i in 1..10) {
                delay(1000)
                Log.e(TAG, "doWork: start work $i")
            }
            showNotification(applicationContext, "title", "Content...")
            val data = workDataOf("key_value" to "SUCCESS")
            Result.success(data)
        } catch (e: Exception) {
            val data = workDataOf("key_value" to "${e.message}")
            Result.failure(data)
        }
    }

    private fun showNotification(context: Context, title: String, content: String) {
        // Create a notification channel for devices running Android Oreo (API 26) and above
        createNotificationChannel(context)

        val pendingIntent = NavDeepLinkBuilder(context)
            .setComponentName(MainActivity::class.java)
            .setGraph(R.navigation.main_nav)
            .setDestination(R.id.imageFragment)
//            .setArguments(bundle)
            .createPendingIntent()
        // Create a notification builder
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.home_spaceship_launch)
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)


        // Get the NotificationManager
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // Show the notification
        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }

    // Create a notification channel (for devices running Android Oreo and above)
    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = CHANNEL_DESCRIPTION
            }

            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {
        const val TAG = "ExampleService"
        const val CHANNEL_ID = "example_channel_id"
        const val CHANNEL_NAME = "Example Channel"
        const val CHANNEL_DESCRIPTION = "This is an example notification channel"
        const val NOTIFICATION_ID = 1
    }

}