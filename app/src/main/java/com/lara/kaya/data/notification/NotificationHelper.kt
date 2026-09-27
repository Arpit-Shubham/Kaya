package com.lara.kaya.data.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.lara.kaya.R

class NotificationHelper(private val context: Context) {

    private val channelId = "kaya_reminders_channel"
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    init {
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Kaya Workout & Goal Reminders",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Ongoing workout session status and nutrition target alerts"
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun showOngoingWorkoutNotification(exerciseName: String, currentSet: Int, totalSets: Int) {
        try {
            val notification = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("KAYA ACTIVE WORKOUT")
                .setContentText("$exerciseName — Set $currentSet / $totalSets in progress")
                .setOngoing(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()

            notificationManager.notify(1001, notification)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun cancelOngoingWorkoutNotification() {
        try {
            notificationManager.cancel(1001)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showGoalAlertNotification(title: String, message: String) {
        try {
            val notification = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .build()

            notificationManager.notify((System.currentTimeMillis() % 10000).toInt(), notification)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
