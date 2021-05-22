package com.medicalapp.donorua.utils.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.medicalapp.donorua.App

fun Context.drawable(id: Int) = ContextCompat.getDrawable(this, id)

fun Activity.simpleNavigate(name: Class<out AppCompatActivity>) =
    startActivity(Intent(this, name))

fun Activity.simpleNavigateAndFinishAfter(name: Class<out AppCompatActivity>) {
    simpleNavigate(name)
    finish()
}

fun Context.centersStorage() = (applicationContext as App).donorCenterStorage