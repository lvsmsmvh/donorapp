package com.medicalapp.donorua.ui.notification.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.medicalapp.donorua.utils.extensions.toNotificationVisit

class PushNotificationReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val notificationVisit = intent.getStringExtra(NotificationHelper.KEY_NOTIFICATION_VISIT)!!
                .toNotificationVisit()

        NotificationHelper.getInstance(context).pushNotificationVisit(notificationVisit)
    }
}