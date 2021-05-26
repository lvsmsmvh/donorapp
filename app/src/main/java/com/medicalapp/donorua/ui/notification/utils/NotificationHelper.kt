package com.medicalapp.donorua.ui.notification.utils

import android.app.*
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.medicalapp.donorua.R
import com.medicalapp.donorua.model.notification.NotificationVisit
import com.medicalapp.donorua.ui.splash.SplashActivity
import com.medicalapp.donorua.utils.extensions.getTimeInStringFormat
import com.medicalapp.donorua.utils.extensions.toJson
import java.util.*

class NotificationHelper private constructor(private val context: Context) {

    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    private val alarmManager = context.getSystemService(Activity.ALARM_SERVICE) as AlarmManager

    fun setNotificationVisit(notificationVisit: NotificationVisit) {
        val intent = Intent(context, PushNotificationReceiver::class.java)
            .putExtra(KEY_NOTIFICATION_VISIT, notificationVisit.toJson())

        val pendingIntent = PendingIntent.getBroadcast(
            context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)

        val notificationTime =
            notificationVisit.visitTime.timeInMillis - notificationVisit.periodBeforeSending.millis

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            notificationTime,
            pendingIntent
        )

        Log.i("tag_notif", "set notif: \n" +
                "notif will trigger in " + notificationTime.toString() + " millis")
    }

    fun pushNotificationVisit(notificationVisit: NotificationVisit) {
        Log.i("tag_notif", "push notif")

        val mIntent = Intent(context, SplashActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)

        val pendingIntent = PendingIntent.getActivity(context,
            0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val title = "Нагадування!"
        val description = getDescriptionForVisitTime(notificationVisit.visitTime)

        initChannels()

        val channelName = if (notificationVisit.withSound)
            CHANNEL_WITH_SOUND_NAME else CHANNEL_NO_SOUND_NAME

        val notification = NotificationCompat.Builder(context, channelName)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setAutoCancel(true)
            .setContentTitle(title)
            .setContentText(description)
            .setContentIntent(pendingIntent)
            .setGroup(GROUP_NAME)
            .build()

        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    private fun initChannels() {
        notificationManager.createNotificationChannel(  // with sound
            NotificationChannel(
                CHANNEL_WITH_SOUND_NAME,
                CHANNEL_WITH_SOUND_NAME,
                NotificationManager.IMPORTANCE_HIGH)
        )

        notificationManager.createNotificationChannel(  // no sound
            NotificationChannel(
                CHANNEL_NO_SOUND_NAME,
                CHANNEL_NO_SOUND_NAME,
                NotificationManager.IMPORTANCE_LOW)
        )
    }


    private fun getDescriptionForVisitTime(time: Calendar) =
        "Наступна кроводача о " + time.getTimeInStringFormat() + " " +
                time.get(Calendar.DAY_OF_MONTH) + "." + time.get(Calendar.MONTH)


    companion object {
        private const val CHANNEL_WITH_SOUND_NAME = "Channel_Donor_Plus_With_Sound"
        private const val CHANNEL_NO_SOUND_NAME = "Channel_Donor_Plus_No_Sound"
        private const val GROUP_NAME = "Group_Donor_Plus"

        private const val NOTIFICATION_ID = 2432353

        const val KEY_NOTIFICATION_VISIT = "key_notification_visit"

        fun getInstance(context: Context) = NotificationHelper(context)
    }

}