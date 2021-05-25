package com.medicalapp.donorua.utils.extensions

import android.view.View
import android.widget.Button
import androidx.core.view.forEach
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.makeUnclickable() {
    alpha = 0.5f
    isClickable = false
}

fun View.makeClickable() {
    alpha = 1f
    isClickable = true
}

fun ChipGroup.findChipWithTheTextAndMarkIt(str: String) {
    forEach {
        with (it as Chip) { if (text == str) isChecked = true }
    }
}

fun ChipGroup.checkedChipText() =
    if (checkedChipId == View.NO_ID) null
    else findViewById<Chip>(checkedChipId).text.toString()