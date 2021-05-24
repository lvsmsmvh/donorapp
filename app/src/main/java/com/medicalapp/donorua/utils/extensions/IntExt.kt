package com.medicalapp.donorua.utils.extensions

import android.content.Context
import android.util.TypedValue

fun Float.toDp(context: Context) =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, context.resources.displayMetrics)