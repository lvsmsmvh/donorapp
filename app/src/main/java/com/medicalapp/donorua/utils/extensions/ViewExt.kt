package com.medicalapp.donorua.utils.extensions

import android.view.View
import androidx.core.view.forEach
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.medicalapp.donorua.model.user.Gender
import kotlinx.android.synthetic.main.first_open_activity.*

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun ChipGroup.findChipWithTheTextAndMarkIt(str: String) {
    forEach {
        with (it as Chip) { if (text == str) isSelected = true }
    }
}

fun ChipGroup.checkedChipText() =
    if (checkedChipId == View.NO_ID) null
    else findViewById<Chip>(checkedChipId).text.toString()