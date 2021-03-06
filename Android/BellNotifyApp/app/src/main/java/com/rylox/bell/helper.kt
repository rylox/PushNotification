package com.rylox.bell

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

object Utility {

    // Notification ID.
    private const val NOTIFICATION_ID = 0

    fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {

        val contentIntent = Intent(applicationContext, MainActivity::class.java)

        val contentPendingIntent = PendingIntent.getActivity(
            applicationContext,
            NOTIFICATION_ID,
            contentIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        // Build the notification
        val builder = NotificationCompat.Builder(
            applicationContext,
            applicationContext.getString(R.string.default_notification_channel_id)
        )
            .setSmallIcon(R.drawable.ic_stat_ic_notification)
            .setContentTitle(applicationContext.getString(R.string.fcm_message))
            .setContentText(messageBody)

            .setContentIntent(contentPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setAutoCancel(true)
        notify(NOTIFICATION_ID, builder.build())
    }

    fun NotificationManager.cancelNotifications() {
        cancelAll()
    }

}