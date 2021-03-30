package com.medicalapp.donorua.utils.extensions

import android.view.View
import android.widget.TextView

fun TextView.show() {
    visibility = View.VISIBLE
}

fun TextView.hide() {
    visibility = View.GONE
}