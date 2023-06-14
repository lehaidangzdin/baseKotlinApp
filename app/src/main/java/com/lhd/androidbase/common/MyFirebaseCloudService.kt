package com.lhd.androidbase.common

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.lhd.androidbase.R
import com.lhd.androidbase.activities.MainActivity2

class MyFirebaseCloudService : FirebaseMessagingService() {

    private val channelId = "MyChannelId"
    private val channelName = "MyChannelName"
    val notificationId = 1

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onMessageReceived(message: RemoteMessage) {
//        message.notification?.let {
//            val title = it.title
//            val body = it.body
//            showNotification(title, body)
//        }
        sendNotification(message)
    }

    private fun getRemoteView(title: String, mess: String): RemoteViews {
        val remoteView = RemoteViews(applicationContext.packageName, R.layout.notifycation)

        remoteView.setTextViewText(R.id.tvTitle, title)
        remoteView.setTextViewText(R.id.tvBody, mess)
//        remoteView.setImageViewResource(R.id.img, R.drawable.home_spaceship_launch)

        return remoteView
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("UnspecifiedImmutableFlag")
    private fun sendNotification(remoteMessage: RemoteMessage) {
        val title: String?
        val mess: String?
        if (remoteMessage.notification?.title != null) {
            title = remoteMessage.notification?.title
            mess = remoteMessage.notification?.body
        } else {
            title = remoteMessage.data["title"]
            mess = remoteMessage.data["body"]
        }

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val intent = Intent(this, MainActivity2::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        // channel id, channel name
        var builder: NotificationCompat.Builder =
            NotificationCompat.Builder(applicationContext, channelId)
                .setSmallIcon(R.drawable.home_spaceship_launch)
                .setAutoCancel(true)
                .setVibrate(longArrayOf(0, 1000, 500, 1000))
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)

        builder = builder.setContent(getRemoteView(title!!, mess!!))


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
            builder.setChannelId(channelId)
        }
        notificationManager.notify(0, builder.build())
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("TOKENNNNN", token)
    }

    private fun showNotification(title: String?, message: String?) {
        val channelId = "MyChannelId"
        val channelName = "MyChannelName"
        val notificationId = 1

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.home_spaceship_launch)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Kiểm tra phiên bản Android và tạo kênh thông báo nếu cần thiết
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(notificationId, notificationBuilder.build())
    }

}